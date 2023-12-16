package l1a.jjakkak.core.domain.user.usecase

import l1a.jjakkak.core.domain.user.UserQuery

interface GetUserUseCase {
    fun getUser(): UserQuery
}