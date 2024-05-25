package com.zzekak.core.domain.user

data class Token(
    val accessToken: String,
    val refreshToken: String
)
