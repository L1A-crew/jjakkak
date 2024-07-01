package com.zzekak.domain.mission

import com.zzekak.ApiUrl
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.model.UpdateMissionStatusCommand
import com.zzekak.domain.mission.request.UpdateMissionRequest
import com.zzekak.domain.mission.response.SearchedMissionResponse
import com.zzekak.domain.mission.usecase.AppointmentMissionUseCase
import com.zzekak.domain.user.UserId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*


internal interface MissionProgressController {

    @GetMapping("${ApiUrl.SEARCH_APPOINT_MISSION}")
    fun searchAppointmentMission(
        @PathVariable(com.zzekak.PathVariable.APPOINTMENT) appointmentId: UUID
    ): SearchedMissionResponse

    @PutMapping("${ApiUrl.UPDATE_MISSION}")
    fun updateMissionStatus(
        @RequestBody body: UpdateMissionRequest
    ): SearchedMissionResponse
}
@RestController
internal class MissionProgressControllerImpl(
    val appointmentMissionUseCase: AppointmentMissionUseCase
) : MissionProgressController {
    override fun searchAppointmentMission(appointmentId: UUID): SearchedMissionResponse  =
        appointmentMissionUseCase.searchAppointMissionStatus(AppointmentId(appointmentId)).run { SearchedMissionResponse.from(this) }

    override fun updateMissionStatus(body: UpdateMissionRequest): SearchedMissionResponse =
        appointmentMissionUseCase.updateMissionStatus(UpdateMissionStatusCommand(
            appointmentMissionId = body.appointmentMissionId,
            appointmentId = AppointmentId(body.appointmentId),
            userId = UserId(body.userId),
            missionStep = body.missionStep,
            completeDateTime = body.completeDateTime
        )).run { SearchedMissionResponse.from(this) }

}
