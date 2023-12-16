package l1a.jjakkak.core.domain.user.message

import l1a.jjakkak.core.domain.auth.AuthenticationType

data class CreateUserMessage(
    val authenticationId: String,
    val type: AuthenticationType
)