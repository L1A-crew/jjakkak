package com.zzekak.domain.user.usecase.common

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.time.Instant
import java.util.Base64
import java.util.UUID

interface JwtMixin {
    fun DecodedJWT.validateExpiry() =
        with(expiresAtAsInstant) {
            if (this.isBefore(Instant.now())) {
                throw TokenExpiredException(EXPIRED_MESSAGE, this)
            }
        }

    fun DecodedJWT.validateSignature(alg: Algorithm): DecodedJWT = JWT.require(alg).build().verify(this)

    fun createToken(
        userId: UUID,
        alg: Algorithm
    ): Pair<String, String> =
        JWT.create()
            .withIssuer(TOKEN_ISSUER)
            .withSubject(userId.toString())
            .withIssuedAt(Instant.now())
            .run {
                val now = Instant.now()

                withExpiresAt(now.plusSeconds(ACCESS_TOKEN_EXPIRY)).sign(alg) to
                    withExpiresAt(now.plusSeconds(REFRESH_TOKEN_EXPIRY)).sign(alg)
            }

    fun getPrivateKey(privateKey: String): RSAPrivateKey {
        val keyBytes = Files.readAllBytes(Paths.get(privateKey))
        return KeyFactory.getInstance(ALG)
            .generatePrivate(PKCS8EncodedKeySpec(keyBytes)) as RSAPrivateKey
    }

    fun getPublicKey(publicKey: String): RSAPublicKey {
        val publicKeyPEM =
            Files.readString(Paths.get(publicKey))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\\s".toRegex(), "")

        val decoded = Base64.getDecoder().decode(publicKeyPEM)
        return KeyFactory.getInstance(ALG)
            .generatePublic(X509EncodedKeySpec(decoded)) as RSAPublicKey
    }

    companion object {
        const val EXPIRED_MESSAGE = "token is expired"

        const val TOKEN_ISSUER = "liaServer"

        const val ACCESS_TOKEN_EXPIRY = 60 * 60L
        const val REFRESH_TOKEN_EXPIRY = 60 * 60 * 24 * 30 * 6L

        const val ALG = "RSA"
    }
}
