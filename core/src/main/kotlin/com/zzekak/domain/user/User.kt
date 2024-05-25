package com.zzekak.core.domain.user

import com.zzekak.core.domain.common.IdTypeUUID
import java.time.Instant
import java.util.UUID

@JvmInline
value class UserId(override val value: UUID) : IdTypeUUID

interface UserCommand {
    val id: UserId
    val name: String
    val authenticationCommand: AuthenticationCommand
    val agreement: Agreement
    val isRemoved: Boolean

    fun deleteUser(): UserCommand =
        UserCommandImpl(
            id = id,
            name = name,
            authenticationCommand = authenticationCommand,
            agreement = agreement,
            isRemoved = true,
        )

    companion object {
        fun create(
            id: UserId,
            name: String,
            authentication: AuthenticationCommand,
            agreement: Agreement,
            isRemoved: Boolean = false
        ): UserCommand =
            UserCommandImpl(
                id = id,
                name = name,
                authenticationCommand = authentication,
                agreement = agreement,
                isRemoved = isRemoved,
            )
    }
}

internal data class UserCommandImpl(
    override val id: UserId,
    override val name: String,
    override val authenticationCommand: AuthenticationCommand,
    override val agreement: Agreement,
    override val isRemoved: Boolean
) : UserCommand

interface UserQuery {
    val id: UserId
    val name: String
    val authentication: AuthenticationQuery
    val agreement: Agreement
    val createdAt: Instant
    val updatedAt: Instant
    val isRemoved: Boolean

    companion object {
        fun create(
            id: UserId,
            name: String,
            authentication: AuthenticationQuery,
            agreement: Agreement,
            createdAt: Instant,
            updatedAt: Instant,
            isRemoved: Boolean = false
        ): UserQuery =
            UserQueryImpl(
                id = id,
                name = name,
                authentication = authentication,
                agreement = agreement,
                createdAt = createdAt,
                updatedAt = updatedAt,
                isRemoved = isRemoved,
            )
    }
}

internal data class UserQueryImpl(
    override val id: UserId,
    override val name: String,
    override val authentication: AuthenticationQuery,
    override val agreement: Agreement,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val isRemoved: Boolean,
) : UserQuery
