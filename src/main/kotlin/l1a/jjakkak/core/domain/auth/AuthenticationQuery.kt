package l1a.jjakkak.core.domain.auth

import l1a.jjakkak.core.domain.common.IdTypeString
import java.time.Instant

@JvmInline
value class AuthenticationId(override val value: String) : IdTypeString

interface AuthenticationCommand {
    val id: AuthenticationId
    val type: AuthenticationType

    companion object {
        fun create(
            id: AuthenticationId,
            type: AuthenticationType
        ): AuthenticationCommand =
            AuthenticationCommandImpl(
                id = id,
                type = type
            )
    }
}

internal data class AuthenticationCommandImpl(
    override val id: AuthenticationId,
    override val type: AuthenticationType
) : AuthenticationCommand

interface AuthenticationQuery : AuthenticationCommand {
    override val id: AuthenticationId
    override val type: AuthenticationType
    val createdAt: Instant
    val updatedAt: Instant

    companion object {
        fun create(
            id: AuthenticationId,
            type: AuthenticationType,
            createdAt: Instant,
            updatedAt: Instant
        ): AuthenticationQuery =
            AuthenticationQueryImpl(
                id = id,
                type = type,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
    }
}

internal class AuthenticationQueryImpl(
    override val id: AuthenticationId,
    override val type: AuthenticationType,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : AuthenticationQuery
