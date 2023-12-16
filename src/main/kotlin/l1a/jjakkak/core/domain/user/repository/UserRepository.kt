package l1a.jjakkak.core.domain.user.repository

import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.core.domain.user.UserQuery

interface UserRepository {
    fun save(user: UserCommand): UserQuery
}