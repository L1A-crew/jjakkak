package com.zzekak.domain.push.model

import java.time.Instant

sealed interface AppointmentPush

data class AppointmentPushCommand(
    val appointmentTime: Instant,
    val departureTime: Instant,
    val createAt: Instant
) : AppointmentPush
