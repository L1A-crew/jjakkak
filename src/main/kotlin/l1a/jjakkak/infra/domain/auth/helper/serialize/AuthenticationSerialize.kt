package l1a.jjakkak.infra.domain.auth.helper.serialize

import l1a.jjakkak.core.domain.auth.AuthenticationCommand
import l1a.jjakkak.core.domain.auth.AuthenticationQuery
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity

interface AuthenticationSerialize {
    fun AuthenticationCommand.toEntity() =
        AuthenticationEntity(
            authenticationId = id.value,
            type = type,
        )
}