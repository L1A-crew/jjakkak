package com.zzekak.core.domain.user.message

import com.zzekak.domain.user.AuthenticationType

data class JoinOrLoginMessage(
    val token: String,
    val type: AuthenticationType
)
