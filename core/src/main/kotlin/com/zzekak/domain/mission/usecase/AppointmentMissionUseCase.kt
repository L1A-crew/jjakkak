package com.zzekak.domain.mission.usecase

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.mission.model.AppointmentMissionCommand
import com.zzekak.domain.mission.model.AppointmentUserMissionQuery
import com.zzekak.domain.mission.model.UpdateMissionStatusCommand
import com.zzekak.domain.mission.repository.AppointmentMissionRepository
import org.springframework.stereotype.Service

@Service
class AppointmentMissionUseCase(
    val appointmentMissionRepo: AppointmentMissionRepository
) {
    fun searchAppointMissionStatus(appointmentId: AppointmentId): List<AppointmentUserMissionQuery> =
        appointmentMissionRepo.findAllByAppointmentId(appointmentId, AppointmentUserMissionQuery::class)

    fun updateMissionStatus(body: UpdateMissionStatusCommand): List<AppointmentUserMissionQuery> = appointmentMissionRepo.updateMissionStatus(body).run { appointmentMissionRepo.findAllByAppointmentId(body.appointmentId, AppointmentUserMissionQuery::class) }

}
