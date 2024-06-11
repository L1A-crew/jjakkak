package com.zzekak.domain.user.reqeust

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
internal data class RefreshTokenRequest(
    val refreshToken: String
)
