package l1a.jjakkak.api.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.core.domain.user.Token

data class TokenResponse(
    @JsonProperty("accessToken")
    val accessToken: String,
    @JsonProperty("refreshToken")
    val refreshToken: String
) {
    companion object {
        fun from(token: Token) =
            TokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
    }
}
