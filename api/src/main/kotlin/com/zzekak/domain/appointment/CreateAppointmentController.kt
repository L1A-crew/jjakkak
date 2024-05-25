package com.zzekak.domain.appointment

import com.zzekak.ApiUrl
import com.zzekak.core.domain.appointment.model.AppointmentCommand
import com.zzekak.core.domain.appointment.model.AppointmentId
import com.zzekak.core.domain.appointment.usecase.CreateAppointmentUseCase
import com.zzekak.core.domain.user.UserId
import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId
import com.zzekak.domain.appointment.request.CreateAppointmentRequest
import com.zzekak.domain.appointment.response.CreateAppointmentResponse
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
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
            appointment =
                with(request) {
                    AppointmentCommand.create(
                        id = AppointmentId(UUID.randomUUID()),
                        ownerId = UserId(id),
                        name = request.name,
                        address =
                            AppointmentAddress.create(
                                id = AppointmentAddressId(UUID.randomUUID()),
                                cityOrProvince = address.cityOrProvince,
                                districtOrCity = address.districtOrCity,
                                postalCode = address.postalCode,
                                roadAddress = address.roadAddress,
                                x = address.x,
                                y = address.y,
                                undergroundYn = address.undergroundYn,
                                buildingName = address.buildingName,
                                jibunAddress = address.jibunAddress,
                            ),
                        appointmentTime = appointmentTime.toInstant(),
                        participants = participants.map { UserId(it) },
                        deleted = false,
                    )
                },
        ).run { CreateAppointmentResponse.from(this) }
}
