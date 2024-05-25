package com.zzekak.core.domain.user.repository

import com.zzekak.core.domain.user.AuthenticationId
import com.zzekak.core.domain.user.UserId
import com.zzekak.core.domain.user.UserQuery
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
