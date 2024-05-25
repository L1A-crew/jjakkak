package com.zzekak.domain.address.request

import java.time.ZonedDateTime

internal data class FindPathRequest(
    val strtLocX: String,
    val strtLocY: String,
    val endLocX: String,
    val endLocY: String,
    val appointmentTime: ZonedDateTime
)
