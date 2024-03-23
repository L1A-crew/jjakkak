package l1a.jjakkak.api.domain.appointment

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.appointment.response.CreateAppointmentResponse
import l1a.jjakkak.core.domain.address.model.Address
import l1a.jjakkak.core.domain.address.model.AddressId
import l1a.jjakkak.core.domain.address.model.IdentifierAddress
import l1a.jjakkak.core.domain.appointment.model.AppointmentCommand
import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import l1a.jjakkak.core.domain.appointment.usecase.CreateAppointmentUseCase
import l1a.jjakkak.core.domain.user.UserId
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
internal interface CreateAppointmentController {
    @PostMapping(ApiUrl.CREATE_APPOINTMENT)
    fun createAppointment(
        @AuthenticationPrincipal id: UUID,
        @RequestBody request: CreateAppointmentRequest
    ): CreateAppointmentResponse
}

@RestController
internal class CreateAppointmentControllerImpl(
    val createAppointmentUseCase: CreateAppointmentUseCase
) : CreateAppointmentController {
    override fun createAppointment(
        id: UUID,
        request: CreateAppointmentRequest
    ): CreateAppointmentResponse =
        createAppointmentUseCase.createAppointment(
            appointment = AppointmentCommand.create(
                id = AppointmentId(UUID.randomUUID()),
                ownerId = UserId(id),
                name = request.name,
                address =
                IdentifierAddress.create(
                    id = AddressId(UUID.randomUUID()),
                    address = Address.create(
                        cityOrProvince = request.address.cityOrProvince,
                        districtOrCity = request.address.districtOrCity,
                        postalCode = request.address.postalCode,
                        jibunAddress = request.address.jibunAddress,
                        roadAddress = request.address.roadAddress
                    )
                ),
                appointmentTime = request.appointmentTime.toInstant(),
                participants = request.participants.map { UserId(it) },
                deleted = false
            )
        ).run { CreateAppointmentResponse.from(this) }
}