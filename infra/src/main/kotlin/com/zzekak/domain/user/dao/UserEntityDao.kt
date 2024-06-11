package com.zzekak.infra.domain.user.dao

import com.zzekak.domain.user.AuthenticationId
import com.zzekak.domain.user.UserId
import com.zzekak.infra.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface UserEntityDao {
    fun save(entity: UserEntity): UserEntity

    fun findUserByAuthenticationId(authenticationId: AuthenticationId): UserEntity?

    fun findUserByAuthenticationIdAndIsRemoved(
        authenticationId: AuthenticationId,
        isRemoved: Boolean
    ): UserEntity?

    fun findUserByUserIdAndIsRemoved(
        userId: UUID,
        isRemoved: Boolean
    ): UserEntity?

    fun findAllByIds(ids: Collection<UserId>): List<UserEntity>
}

@Repository
/** 쿼리메소드 사용으로 어쩔수 없이 FunctionName 경고에 대해 Suppress 한다 */
@Suppress("FunctionName")
interface UserEntityJpaRepository : JpaRepository<UserEntity, UUID> {
    fun findByAuthenticationEntity_AuthenticationId(authenticationId: String): UserEntity?

    fun findByAuthenticationEntity_AuthenticationIdAndIsRemoved(
        authenticationId: String,
        isRemoved: Boolean
    ): UserEntity?

    fun findByUserIdAndAndIsRemoved(
        userId: UUID,
        isRemoved: Boolean
    ): UserEntity?

    fun findAllByUserIdIn(ids: Collection<UUID>): List<UserEntity>
}

@Repository
internal class UserEntityDaoImpl(
    val delegate: UserEntityJpaRepository
) : UserEntityDao {
    override fun save(entity: UserEntity): UserEntity = delegate.save(entity)

    override fun findUserByAuthenticationId(authenticationId: AuthenticationId): UserEntity? =
        delegate.findByAuthenticationEntity_AuthenticationId(authenticationId.value)

    override fun findUserByAuthenticationIdAndIsRemoved(
        authenticationId: AuthenticationId,
        isRemoved: Boolean
    ): UserEntity? = delegate.findByAuthenticationEntity_AuthenticationIdAndIsRemoved(authenticationId.value, isRemoved)

    override fun findUserByUserIdAndIsRemoved(
        userId: UUID,
        isRemoved: Boolean
    ): UserEntity? = delegate.findByUserIdAndAndIsRemoved(userId, isRemoved)

    override fun findAllByIds(ids: Collection<UserId>): List<UserEntity> =
        delegate.findAllByUserIdIn(ids.map { it.value })
}
