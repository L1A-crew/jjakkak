package l1a.jjakkak.api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
internal class JwtTokenProvider(
    @Value("\${jwt.secret-key:}")
    private val securityKey: String
) {
    private val alg = Algorithm.HMAC256(securityKey)

    fun createToken(userId: UUID, now: Instant = Instant.now()): String =
        JWT.create()
            .withSubject(userId.toString())
            .withIssuedAt(now)
            .withExpiresAt(now.plusSeconds(AUTH_VALIDATION_EXPIRY))
            .sign(alg)

    fun validationToken(token: String, now: Instant = Instant.now()): Boolean {
        val jwt = decodeToken(token)

        return now.isBefore(jwt.expiresAt.toInstant())
    }

    fun getUserId(token: String): UUID = UUID.fromString(decodeToken(token).subject)

    fun getAuthentication(token: String): Authentication =
        PreAuthenticatedAuthenticationToken(decodeToken(token), null, emptyList())

    private fun decodeToken(token: String): DecodedJWT =
        JWT.require(alg)
            .build()
            .verify(token)

    companion object {
        const val AUTH_VALIDATION_EXPIRY: Long = 60 * 60 * 24 * 30
    }
}