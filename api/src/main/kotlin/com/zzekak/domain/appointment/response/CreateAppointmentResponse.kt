package com.zzekak.domain.appointment.response

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.common.response.AddressResponse
import com.zzekak.domain.user.UserId
import java.time.Instant

internal data class CreateAppointmentResponse(
    val id: AppointmentId,
    val ownerId: UserId,
    val appointmentName: String,
    val address: AddressResponse,
    val appointmentTime: Instant,
    val createdAt: Instant,
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
