package l1a.jjakkak.api.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.api.domain.user.dto.AuthenticationTypeDto
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.UserQuery
import java.time.Instant
import java.util.UUID

data class UserCreateResponse(
    @JsonProperty("userId")
    val userId: UserId,

    @JsonProperty("authenticationType")
    val authenticationType: AuthenticationTypeDto,

    @JsonProperty("createdAt")
    val createdAt: Instant,

    @JsonProperty("updatedAt")
    val updatedAt: Instant
) {

    companion object {
        fun from(src: UserQuery) =
            UserCreateResponse(
                userId = src.id,
                authenticationType = AuthenticationTypeDto.from(src.authentication.type),
                createdAt = src.createdAt,
                updatedAt = src.updatedAt
            )
    }
}