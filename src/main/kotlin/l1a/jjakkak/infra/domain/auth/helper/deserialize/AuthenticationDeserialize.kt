package l1a.jjakkak.infra.domain.auth.helper.deserialize

import l1a.jjakkak.core.domain.auth.AuthenticationId
import l1a.jjakkak.core.domain.auth.AuthenticationQuery
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity

interface AuthenticationDeserialize {
    fun AuthenticationEntity.toDomain(): AuthenticationQuery =
        AuthenticationQuery.create(
            id = AuthenticationId(authenticationId),
            type = type,
            createdAt = createdAt!!,
            updatedAt = updatedAt!!
        )
}