package l1a.jjakkak.api.domain.appointment.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.api.domain.common.response.AddressResponse
import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery
import l1a.jjakkak.core.domain.user.UserId
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
                    address = AddressResponse.from(address), appointmentTime = appointmentTime,
                    createdAt = createdAt,
                    updatedAt = updatedAt
                )
            }
    }
}
