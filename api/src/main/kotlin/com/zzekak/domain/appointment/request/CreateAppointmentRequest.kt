package com.zzekak.domain.appointment.request

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.com.zzekak.domain.address.request.AddressRequest
import java.time.ZonedDateTime
import java.util.UUID

internal data class CreateAppointmentRequest(
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("address")
    val address: AddressRequest,
    @JsonProperty("appointmentTime")
    val appointmentTime: ZonedDateTime,
    @JsonProperty("participants")
    val participants: List<UUID>
)
