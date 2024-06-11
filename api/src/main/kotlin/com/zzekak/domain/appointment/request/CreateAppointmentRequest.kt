package com.zzekak.domain.appointment.request

import com.zzekak.domain.address.request.AddressRequest
import java.time.ZonedDateTime
import java.util.UUID

internal data class CreateAppointmentRequest(
    val name: String = "",
    val address: AddressRequest,
    val appointmentTime: ZonedDateTime,
    val participants: List<UUID>
)
