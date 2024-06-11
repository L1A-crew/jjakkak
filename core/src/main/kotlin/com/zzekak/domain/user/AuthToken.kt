package com.zzekak.domain.user

import com.auth0.jwt.exceptions.TokenExpiredException
import java.time.Instant

data class AuthToken(
    val header: Header,
    val payload: Payload,
    val signature: String
) {
    fun validate() =
        with(Instant.ofEpochSecond(payload.exp)) {
            if (this.isBefore(Instant.now())) {
                throw TokenExpiredException(EXPIRED_MESSAGE, this)
            }
        }

    data class Header(
        val alg: String,
        val typ: String,
        val kid: String
    )

    data class Payload(
        val iss: String,
        val aud: String,
        val sub: String,
        val iat: String,
        val auth_time: String,
        val exp: Long,
    )

    companion object {
        const val EXPIRED_MESSAGE = "token is expired"
    }
}
