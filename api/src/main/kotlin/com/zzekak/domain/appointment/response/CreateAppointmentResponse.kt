package com.zzekak.domain.appointment.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.zzekak.core.domain.appointment.model.AppointmentId
import com.zzekak.core.domain.appointment.model.AppointmentQuery
import com.zzekak.core.domain.user.UserId
import com.zzekak.domain.common.response.AddressResponse
import java.time.Instant

internal data class CreateAppointmentResponse(
    @JsonProperty("id")
    val id: AppointmentId,
    @JsonProperty("ownerId")
    val ownerId: UserId,
    @JsonProperty("appointmentName")
    val appointmentName: String,
    @JsonProperty("address")
    val address: AddressResponse,
    @JsonProperty("appointmentTime")
    val appointmentTime: Instant,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant
) {
    companion object {
        fun from(src: AppointmentQuery) =
            with(src) {
                CreateAppointmentResponse(
                    id = id,
                    ownerId = ownerId,
                    appointmentName = name,
                    address = AddressResponse.from(address),
                    appointmentTime = appointmentTime,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
    }
}
