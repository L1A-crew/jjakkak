package l1a.jjakkak.infra.domain.user.repository

import l1a.jjakkak.core.domain.user.Agreement
import l1a.jjakkak.core.domain.user.AuthenticationId
import l1a.jjakkak.core.domain.user.AuthenticationQuery
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.UserQuery
import l1a.jjakkak.core.domain.user.repository.UserQueryRepository
import l1a.jjakkak.infra.domain.user.dao.UserEntityDao
import l1a.jjakkak.infra.domain.user.entity.AuthenticationEntity
import l1a.jjakkak.infra.domain.user.entity.UserEntity
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
        UserQuery.create(
            id = UserId(userId),
            name = name,
            authentication = authenticationEntity.toDomainQuery(),
            agreement =
                Agreement.create(
                    marketingConsent = marketingConsent,
                    locationConsent = locationConsent,
                    pushNotificationConsent = pushNotificationConsent,
                ),
            createdAt = createdAt,
            updatedAt = updatedAt,
            isRemoved = isRemoved,
        )

    private fun AuthenticationEntity.toDomainQuery(): AuthenticationQuery =
        AuthenticationQuery.create(
            id = AuthenticationId(authenticationId),
            type = type,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
