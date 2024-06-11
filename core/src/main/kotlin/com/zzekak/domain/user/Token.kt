package com.zzekak.domain.user

data class Token(
    val accessToken: String,
    val refreshToken: String
)
