package com.zzekak.domain.user.response

import com.zzekak.domain.user.Token

/**
 * @since 2024-06-09
 */
internal data class JoinOrLoginResponse(
    val tokenContent: TokenResponse,
    val isLoginFirst: Boolean
) {
    companion object {
        fun from(
            token: Token,
            isLoginFirst: Boolean
        ) = JoinOrLoginResponse(
            tokenContent =
                TokenResponse(
                    accessToken = token.accessToken,
                    refreshToken = token.refreshToken,
                ),
            isLoginFirst = isLoginFirst,
        )
    }
}
