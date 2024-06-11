package com.zzekak.infra.domain.user.repository

import com.zzekak.domain.user.UserCommand
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.repository.UserCommandRepository
import com.zzekak.infra.domain.user.dao.UserEntityDao
import com.zzekak.infra.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class UserCommandRepositoryImpl(
    val userEntityDao: UserEntityDao,
) : UserCommandRepository {
    @Transactional
    override fun save(user: UserCommand): UserCommand = userEntityDao.save(UserEntity.from(user)).toDomain()

    @Transactional
    override fun findById(userId: UserId): UserCommand? =
        userEntityDao.findUserByUserIdAndIsRemoved(userId.value, false)?.toDomain()
}
