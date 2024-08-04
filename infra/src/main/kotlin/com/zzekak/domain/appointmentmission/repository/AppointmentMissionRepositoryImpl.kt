package com.zzekak.domain.appointmentmission.repository

import com.zzekak.domain.appointment.dao.AppointmentEntityDao
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointmentmission.dao.AppointmentMissionEntityDao
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionId
import com.zzekak.domain.mission.model.AppointmentMission
import com.zzekak.domain.mission.model.AppointmentUserMissionQuery
import com.zzekak.domain.mission.model.UpdateMissionStatusCommand
import com.zzekak.domain.mission.repository.AppointmentMissionRepository
import com.zzekak.domain.user.UserId
import com.zzekak.exception.ExceptionEnum
import com.zzekak.exception.ZzekakException
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime
import kotlin.reflect.KClass

@Repository
internal class AppointmentMissionRepositoryImpl(
    val amDao: AppointmentMissionEntityDao,
    val appointmentDao: AppointmentEntityDao,
) : AppointmentMissionRepository {
    override fun <T : AppointmentMission> findAllByAppointmentId(
        appointmentId: AppointmentId,
        returnType: KClass<out T>
    ): List<T> = amDao.findByAppointmentId(appointmentId.value)

    override fun <T : AppointmentMission> updateMissionStatus(
        cmd: UpdateMissionStatusCommand,
        returnType: KClass<out T>
    ): T {
        //미션 상태 업데이트
        val apnt = amDao.findByAppointmentId(AppointmentMissionId(
            appointmentId = cmd.appointmentId.value,
            missionId = cmd.missionId,
            userId = cmd.userId.value,
            ))
        if(apnt == null) throw ZzekakException(ExceptionEnum.MISSION_PHASE_CODE_NOT_EXIST)

        val updateEntity = apnt.updateEntity()
        return amDao.save(updateEntity).run{this.toAppointmentMissionQuery() as T}
    }


    fun AppointmentMissionEntity.updateEntity(): AppointmentMissionEntity {
        return AppointmentMissionEntity(
            id = this.id,
            appointment = this.appointment,
            user = this.user,
            mission = this.mission,
            phaseCd = this.phaseCd,
            completeAt = ZonedDateTime.now().toInstant()
        )
    }

    fun AppointmentMissionEntity.toAppointmentMissionQuery(): AppointmentUserMissionQuery =
        AppointmentUserMissionQuery(
            appointmentId = AppointmentId(this.appointment!!.appointmentId),
            missionId = this.mission.missionId!!,
            userId = UserId(this.user.userId),
            phaseCd = this.phaseCd,
            userName = this.user.name,
            complateAt = this.completeAt
        )
}
