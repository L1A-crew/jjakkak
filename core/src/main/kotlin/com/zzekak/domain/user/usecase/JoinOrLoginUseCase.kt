package com.zzekak.domain.user.usecase

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.zzekak.domain.user.Agreement
import com.zzekak.domain.user.AuthToken
import com.zzekak.domain.user.AuthenticationCommand
import com.zzekak.domain.user.AuthenticationId
import com.zzekak.domain.user.AuthenticationType
import com.zzekak.domain.user.Token
import com.zzekak.domain.user.UserCommand
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.message.JoinOrLoginMessage
import com.zzekak.domain.user.repository.AuthRepository
import com.zzekak.domain.user.repository.UserCommandRepository
import com.zzekak.domain.user.repository.UserQueryRepository
import com.zzekak.domain.user.usecase.common.JwtMixin
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

interface JoinOrLoginUseCase {
    fun joinOrLogin(message: JoinOrLoginMessage): Pair<Token, Boolean>
}

@Service
internal class JoinOrLoginUseCaseImpl(
    val userCommandRepo: UserCommandRepository,
    val userQueryRepo: UserQueryRepository,
    val authRepo: AuthRepository,
    @Value("\${lia.auth.private-key-path}")
    private val privateKey: String,
    @Value("\${lia.auth.public-key-path}")
    private val publicKey: String,
    @Value("\${lia.auth.social-login-app-key}")
    private val appKey: String
) : JoinOrLoginUseCase, JwtMixin, InitializingBean {
    lateinit var algorithm: Algorithm

    override fun afterPropertiesSet() {
        algorithm = Algorithm.RSA256(getPublicKey(publicKey), getPrivateKey(privateKey))
    }

    override fun joinOrLogin(message: JoinOrLoginMessage): Pair<Token, Boolean> {
        val (token, type) = message
        var isFirstLogin = false
        val decodedToken = type.decode(token)

        type.validate(
            token = token,
            appKey = appKey,
            rsaPublicKeyInfo = type.getRSAPublicKeyInfo(decodedToken),
        )

        val userId =
            userQueryRepo
                .findUserByAuthenticationIdAndIsRemoved(
                    AuthenticationId(decodedToken.payload.sub),
                    false,
                )?.id // 삭제여부 컬럼 추가
                ?: createUser(decodedToken, type).run {
                    isFirstLogin = true
                    userCommandRepo.save(this).id
                }

        val (accessToken, refreshToken) = createToken(userId.value, algorithm)

        return Token(
            accessToken = accessToken,
            refreshToken = refreshToken,
        ) to isFirstLogin
    }

    private fun createUser(
        authToken: AuthToken,
        type: AuthenticationType
    ) = UserCommand(
        id = UserId(UUID.randomUUID()),
        name = "",
        authenticationCommand =
            AuthenticationCommand(
                id = AuthenticationId(authToken.payload.sub),
                type = type,
            ),
        agreement = Agreement.EMPTY,
        isRemoved = false,
        fcmKey = ""
    )

    private fun AuthenticationType.getRSAPublicKeyInfo(authToken: AuthToken): AuthRepository.RSAPublicKeyInfo =
        when (this) {
            AuthenticationType.KAKAO -> authRepo.getKakaoLoginPublicKey()
            AuthenticationType.APPLE -> authRepo.getAppleLoginPublicKey()
        }
            .find { it.kid == authToken.header.kid }
            ?: throw JWTVerificationException("Not Found Kakao PublicKeyInfo")
}
