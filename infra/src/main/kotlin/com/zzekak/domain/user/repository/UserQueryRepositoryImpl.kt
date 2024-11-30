package com.zzekak.domain.user.repository

import com.zzekak.domain.user.Agreement
import com.zzekak.domain.user.AuthenticationId
import com.zzekak.domain.user.AuthenticationQuery
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.UserQuery
import com.zzekak.domain.user.dao.UserEntityDao
import com.zzekak.domain.user.entity.AuthenticationEntity
import com.zzekak.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.text.MessageFormat
import java.util.UUID

@Repository
internal class UserQueryRepositoryImpl(
    val userEntityDao: UserEntityDao
) : UserQueryRepository {
    @Transactional(readOnly = true)
    override fun findById(userId: UserId): UserQuery? =
        userEntityDao.findUserByUserIdAndIsRemoved(userId.value, false)?.toDomainOfQuery()

    @Transactional(readOnly = true)
    override fun getById(userId: UserId): UserQuery =
        userEntityDao.findUserByUserIdAndIsRemoved(userId.value, false)
            ?.toDomainOfQuery()
            ?: throw NoSuchElementException(MessageFormat.format("User not found. userId: {0}", userId))

    @Transactional(readOnly = true)
    override fun findUserByAuthenticationIdAndIsRemoved(
        authenticationId: AuthenticationId,
        isRemoved: Boolean
    ): UserQuery? = userEntityDao.findUserByAuthenticationIdAndIsRemoved(authenticationId, isRemoved)?.toDomainOfQuery()

    override fun findUserByUserIdAndIsRemoved(
        userId: UUID,
        isRemoved: Boolean
    ): UserQuery? {
        return userEntityDao.findUserByUserIdAndIsRemoved(userId, isRemoved)?.toDomainOfQuery()
    }

    private fun UserEntity.toDomainOfQuery(): UserQuery =
        UserQuery(
            id = UserId(userId),
            name = name,
            profileImageUrl = profileImageUrl,
            authentication = authenticationEntity.toDomainQuery(),
            agreement =
                Agreement(
                    marketingConsent = marketingConsent,
                    locationConsent = locationConsent,
                    pushNotificationConsent = pushNotificationConsent,
                ),
            createdAt = createdAt,
            updatedAt = updatedAt,
            isRemoved = isRemoved,
            fcmKey = if(fcmKey == null){""}else{fcmKey},
        )

    private fun AuthenticationEntity.toDomainQuery(): AuthenticationQuery =
        AuthenticationQuery(
            id = AuthenticationId(authenticationId),
            type = type,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
