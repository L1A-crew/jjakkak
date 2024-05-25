package com.zzekak.domain.user

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.module.kotlin.readValue
import com.zzekak.core.domain.user.AuthToken
import com.zzekak.core.domain.user.repository.AuthRepository
import com.zzekak.util.ObjectMapper.objectMapper
import java.math.BigInteger
import java.security.KeyFactory
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAPublicKeySpec
import java.util.Base64

enum class AuthenticationType(
    @JsonValue val code: String
) {
    KAKAO(code = "ka") {
        override fun decode(token: String): AuthToken {
            val (header, payload, sig) =
                token.split(KAKAO_ID_TOKEN_DELIMITER)
                    .map { Base64.getUrlDecoder().decode(it) }
                    .map { String(it) }

            val decodedHeader = objectMapper.readValue<AuthToken.Header>(header)
            val decodedPayload = objectMapper.readValue<AuthToken.Payload>(payload)

            return AuthToken(
                header = decodedHeader,
                payload = decodedPayload,
                signature = sig,
            )
        }

        override fun validate(
            token: String,
            appKey: String,
            rsaPublicKeyInfo: AuthRepository.RSAPublicKeyInfo
        ) {
            val publicKey = buildPublicKey(rsaPublicKeyInfo) as RSAPublicKey

            val alg = Algorithm.RSA256(publicKey, null)

            val verifier =
                JWT.require(alg)
                    .withIssuer("https://kauth.kakao.com")
//                    .withAudience(appKey)
                    .build()

            verifier.verify(token)
        }
    },
    APPLE(code = "ap") {
        override fun decode(token: String): AuthToken {
            TODO()
        }

        override fun validate(
            token: String,
            appKey: String,
            rsaPublicKeyInfo: AuthRepository.RSAPublicKeyInfo
        ) {
            TODO("Not yet implemented")
        }
    };

    abstract fun decode(token: String): AuthToken

    abstract fun validate(
        token: String,
        appKey: String,
        rsaPublicKeyInfo: AuthRepository.RSAPublicKeyInfo
    )

    companion object {
        const val KAKAO_ID_TOKEN_DELIMITER = '.'

        fun from(code: String): AuthenticationType =
            entries.firstOrNull { it.code == code }
                ?: throw IllegalArgumentException("$code is illegal Argument [AuthenticationType]")

        private fun buildPublicKey(rsaPublicKeyInfo: AuthRepository.RSAPublicKeyInfo): PublicKey {
            val decoder = Base64.getUrlDecoder()
            val modulus = BigInteger(1, decoder.decode(rsaPublicKeyInfo.n))
            val exponent = BigInteger(1, decoder.decode(rsaPublicKeyInfo.e))

            val spec = RSAPublicKeySpec(modulus, exponent)
            val factory = KeyFactory.getInstance(rsaPublicKeyInfo.kty)

            return factory.generatePublic(spec)
        }
    }
}
