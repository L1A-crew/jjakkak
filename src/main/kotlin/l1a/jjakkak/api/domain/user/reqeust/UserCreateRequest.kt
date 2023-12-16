package l1a.jjakkak.api.domain.user.reqeust

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import l1a.jjakkak.api.domain.user.dto.AuthenticationTypeDto
import l1a.jjakkak.core.domain.auth.AuthenticationType
import l1a.jjakkak.core.domain.user.message.CreateUserMessage

@JsonDeserialize
data class UserCreateRequest(
    @JsonProperty("authenticationId")
    val authenticationId: String,
    @JsonProperty("authenticationType")
    val authenticationType: AuthenticationTypeDto
) {
    fun toMessage(): CreateUserMessage =
        CreateUserMessage(
            authenticationId = authenticationId,
            type = AuthenticationType.from(authenticationType.code)
        )
}
