package com.zzekak.domain.user.repository

import com.zzekak.domain.user.AuthenticationId
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.UserQuery
import java.util.UUID

interface UserQueryRepository {
    fun findById(userId: UserId): UserQuery?

    fun getById(userId: UserId): UserQuery

    fun findUserByAuthenticationIdAndIsRemoved(
        authenticationId: AuthenticationId,
        isRemoved: Boolean
    ): UserQuery?

    fun findUserByUserIdAndIsRemoved(
        userId: UUID,
        isRemoved: Boolean
    ): UserQuery?
}
