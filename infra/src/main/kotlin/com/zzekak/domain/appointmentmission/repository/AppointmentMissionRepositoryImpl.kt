package com.zzekak.domain.appointmentmission.repository

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointmentmission.dao.AppointmentMissionEntityDao
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionId
import com.zzekak.domain.mission.model.AppointmentMission
import com.zzekak.domain.mission.model.UpdateMissionStatusCommand
import com.zzekak.domain.mission.repository.AppointmentMissionRepository
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass

@Repository
internal class AppointmentMissionRepositoryImpl(
    val amDao: AppointmentMissionEntityDao,
) : AppointmentMissionRepository {
    override fun <T : AppointmentMission> findAllByAppointmentId(
        appointmentId: AppointmentId,
        returnType: KClass<out T>
    ): List<T> = amDao.findByAppointmentId(appointmentId.value)


}
