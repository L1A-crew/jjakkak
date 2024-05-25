package com.zzekak.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.zzekak.core.domain.user.usecase.common.JwtMixin
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
internal class JwtTokenProvider(
    @Value("\${lia.auth.private-key-path}")
    private val privateKey: String,
    @Value("\${lia.auth.public-key-path}")
    private val publicKey: String
) : JwtMixin, InitializingBean {
    lateinit var alg: Algorithm

    override fun afterPropertiesSet() {
        alg = Algorithm.RSA256(getPublicKey(publicKey), getPrivateKey(privateKey))
    }

    fun validate(
        token: String,
        now: Instant = Instant.now()
    ) {
        JWT.decode(token).also {
            it.validateExpiry()
            it.validateSignature(alg)
        }
    }

    fun getAuthentication(token: String): Authentication =
        PreAuthenticatedAuthenticationToken(
            UUID.fromString(decodeToken(token).subject),
            null,
            emptyList(),
        )

    private fun decodeToken(token: String): DecodedJWT =
        JWT.require(alg)
            .build()
            .verify(token)
}
