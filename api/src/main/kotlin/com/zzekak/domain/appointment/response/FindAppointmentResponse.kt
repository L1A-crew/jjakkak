package com.zzekak.domain.appointment.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.zzekak.core.domain.appointment.model.AppointmentId
import com.zzekak.core.domain.appointment.model.AppointmentQuery
import com.zzekak.core.domain.user.UserId
import com.zzekak.domain.common.response.AddressResponse
import java.time.Instant

internal data class FindAppointmentResponse(
    @JsonProperty("content")
    val content: List<FindAppointmentContent>
) {
    companion object {
        fun from(src: Collection<AppointmentQuery>): FindAppointmentResponse =
            FindAppointmentResponse(
                content =
                    src.map {
                        FindAppointmentContent(
                            id = it.id,
                            appointmentName = it.name,
                            address = AddressResponse.from(it.address),
                            appointmentTime = it.appointmentTime,
                            participants = it.participants,
                        )
                    },
            )
    }
}

internal data class FindAppointmentContent(
    @JsonProperty("id")
    val id: AppointmentId,
    @JsonProperty("appointmentName")
    val appointmentName: String,
    @JsonProperty("address")
    val address: AddressResponse,
    @JsonProperty("appointmentTime")
    val appointmentTime: Instant,
    @JsonProperty("participants")
    val participants: List<UserId>,
)
