package l1a.jjakkak.infra.domain.auth.dao

import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface SocialAuthenticationDao {
    fun save(authenticationEntity: AuthenticationEntity): AuthenticationEntity
}

@Repository
interface AuthenticationEntityJpaRepository : JpaRepository<AuthenticationEntity, String>

@Repository
class SocialAuthenticationDaoImpl(
    val delegate: AuthenticationEntityJpaRepository
) : SocialAuthenticationDao {
    override fun save(
        authenticationEntity: AuthenticationEntity
    ): AuthenticationEntity = delegate.save(authenticationEntity)
}
