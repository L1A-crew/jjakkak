package l1a.jjakkak.core.domain.user.usecase

import l1a.jjakkak.core.domain.auth.AuthenticationCommand
import l1a.jjakkak.core.domain.auth.AuthenticationId
import l1a.jjakkak.core.domain.common.IdTypeUUID
import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.core.domain.user.UserQuery
import l1a.jjakkak.core.domain.user.message.CreateUserMessage
import l1a.jjakkak.core.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

interface CreateUserUseCase {
    fun createUser(message: CreateUserMessage): UserQuery
}

@Service
internal class CreateUserUseCaseImpl(
    val userRepo: UserRepository
) : CreateUserUseCase {
    override fun createUser(message: CreateUserMessage): UserQuery =
        UserCommand.create(
            id = IdTypeUUID.random(),
            authentication = AuthenticationCommand.create(
                id = AuthenticationId(value = message.authenticationId),
                type = message.type
            )
        ).run { userRepo.save(this) }
}