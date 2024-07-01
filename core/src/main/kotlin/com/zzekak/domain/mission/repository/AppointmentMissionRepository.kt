package com.zzekak.domain.mission.repository

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.model.AppointmentMission
import com.zzekak.domain.mission.model.AppointmentMissionCommand
import com.zzekak.domain.mission.model.UpdateMissionStatusCommand
import java.util.UUID
import kotlin.reflect.KClass

interface AppointmentMissionRepository {
    fun <T : AppointmentMission> findAllByAppointmentId(
        appointmentId: AppointmentId,
        returnType: KClass<out T>
    ): List<T>

    fun updateMissionStatus(mission: UpdateMissionStatusCommand)
}
