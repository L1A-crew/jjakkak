package com.zzekak.domain.user.reqeust

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
internal data class RefreshTokenRequest(
    @JsonProperty("refreshToken")
    val refreshToken: String
)
