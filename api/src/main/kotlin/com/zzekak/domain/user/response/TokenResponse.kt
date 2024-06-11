package com.zzekak.domain.user.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.domain.user.Token

@JsonSerialize
internal data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(token: Token) =
            TokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken,
            )
    }
}
