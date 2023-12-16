package l1a.jjakkak.infra.domain.user.repository

import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.core.domain.user.UserQuery
import l1a.jjakkak.core.domain.user.repository.UserRepository
import l1a.jjakkak.infra.domain.user.dao.UserEntityDao
import l1a.jjakkak.infra.domain.user.helper.deserialize.UserDeserialize
import l1a.jjakkak.infra.domain.user.helper.serialize.UserSerialize
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class UserRepositoryImpl(
   val userEntityDao: UserEntityDao
): UserRepository, UserSerialize, UserDeserialize {
    @Transactional
    override fun save(user: UserCommand): UserQuery = userEntityDao.save(user.toEntity()).toDomain()
}