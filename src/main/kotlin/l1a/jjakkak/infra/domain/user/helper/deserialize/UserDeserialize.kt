package l1a.jjakkak.infra.domain.user.helper.deserialize

import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.UserQuery
import l1a.jjakkak.infra.domain.auth.helper.deserialize.AuthenticationDeserialize
import l1a.jjakkak.infra.domain.user.entity.UserEntity

interface UserDeserialize: AuthenticationDeserialize {
    fun UserEntity.toDomain(): UserQuery =
        UserQuery.create(
            id = UserId(userId!!),
            authentication = authenticationEntity.toDomain(),
            createdAt = createdAt!!,
            updatedAt = updatedAt!!
        )
}