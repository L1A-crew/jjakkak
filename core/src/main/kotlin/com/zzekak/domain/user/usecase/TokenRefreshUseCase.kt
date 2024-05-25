package com.zzekak.core.domain.user.usecase

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.zzekak.core.domain.user.Token
import com.zzekak.core.domain.user.usecase.common.JwtMixin
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

interface TokenRefreshUseCase {
    fun refresh(refreshToken: String): Token
}

@Service
internal class TokenRefreshUseCaseImpl(
    @Value("\${lia.auth.private-key-path}")
    private val privateKey: String,
    @Value("\${lia.auth.public-key-path}")
    private val publicKey: String
) : TokenRefreshUseCase, JwtMixin {
    override fun refresh(refreshToken: String): Token {
        val algorithm: Algorithm = Algorithm.RSA256(getPublicKey(publicKey), getPrivateKey(privateKey))

        val decoded =
            JWT.decode(refreshToken).also {
                it.validateExpiry()
                it.validateSignature(algorithm)
            }

        val userId = UUID.fromString(decoded.subject)

        val (accessToken, _) = createToken(userId, algorithm)

        return Token(accessToken, "")
    }
}
