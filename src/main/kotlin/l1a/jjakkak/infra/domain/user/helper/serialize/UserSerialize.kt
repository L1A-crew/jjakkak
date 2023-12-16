package l1a.jjakkak.infra.domain.user.helper.serialize

import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.infra.domain.auth.helper.serialize.AuthenticationSerialize
import l1a.jjakkak.infra.domain.user.entity.UserEntity


interface UserSerialize: AuthenticationSerialize {
    fun UserCommand.toEntity() =
        UserEntity(
            userId = id.value,
            authenticationEntity = authentication.toEntity()
        ).apply {
            authenticationEntity.userEntity = this
        }
}