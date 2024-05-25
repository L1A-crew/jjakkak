package com.zzekak.infra.domain.user.dao

import com.zzekak.infra.domain.user.entity.AuthenticationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface SocialAuthenticationDao {
    fun save(authenticationEntity: AuthenticationEntity): AuthenticationEntity
}

@Repository
interface AuthenticationEntityJpaRepository : JpaRepository<AuthenticationEntity, String>

@Repository
class SocialAuthenticationDaoImpl(
    val delegate: AuthenticationEntityJpaRepository
) : SocialAuthenticationDao {
    override fun save(authenticationEntity: AuthenticationEntity): AuthenticationEntity =
        delegate.save(authenticationEntity)
}
