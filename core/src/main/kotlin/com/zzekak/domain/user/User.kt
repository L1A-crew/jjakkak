package com.zzekak.domain.user

import com.zzekak.domain.common.IdTypeUUID
import java.time.Instant
import java.util.UUID

@JvmInline
value class UserId(override val value: UUID) : IdTypeUUID

data class UserCommand(
    val id: UserId,
    val name: String,
    val profileImageUrl: String,
    val authenticationCommand: AuthenticationCommand,
    val agreement: Agreement,
    val isRemoved: Boolean,
    val fcmKey: String
) {
    fun deleteUser(): UserCommand = this.copy(isRemoved = true)
}

data class UserQuery(
    val id: UserId,
    val name: String,
    val profileImageUrl: String,
    val authentication: AuthenticationQuery,
    val agreement: Agreement,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isRemoved: Boolean,
    val fcmKey: String
)
