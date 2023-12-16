package l1a.jjakkak.infra.domain.user.dao

import l1a.jjakkak.infra.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface UserEntityDao {
    fun save(entity: UserEntity): UserEntity
}

@Repository
interface UserEntityJpaRepository: JpaRepository<UserEntity, UUID>

@Repository
internal class UserEntityDaoImpl(
    val delegate: UserEntityJpaRepository
): UserEntityDao {
    override fun save(entity: UserEntity): UserEntity = delegate.save(
        entity
            .also {
                val a = it
                println(a)
            })
}