package com.zzekak.domain.user

import com.zzekak.domain.common.IdTypeUUID
import java.time.Instant
import java.util.UUID

@JvmInline
value class UserId(override val value: UUID) : IdTypeUUID

data class UserCommand(
    val id: UserId,
    val name: String,
    val authenticationCommand: AuthenticationCommand,
    val agreement: Agreement,
    val isRemoved: Boolean
) {
    fun deleteUser(): UserCommand = this.copy()
}

data class UserQuery(
    val id: UserId,
    val name: String,
    val authentication: AuthenticationQuery,
    val agreement: Agreement,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isRemoved: Boolean
)
