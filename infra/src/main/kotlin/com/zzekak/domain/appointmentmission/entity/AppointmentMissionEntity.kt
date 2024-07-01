package com.zzekak.domain.appointmentmission.entity

import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity.Companion.TABLE_APPOINTMENT_MISSION
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.mission.model.UpdateMissionStatusCommand
import com.zzekak.domain.user.entity.UserEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_APPOINTMENT_MISSION)
@EntityListeners(AuditingEntityListener::class)
class AppointmentMissionEntity(

    @EmbeddedId
    val id: AppointmentMissionId,


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("appointmentId")
    @JoinColumn(name = AppointmentEntity.COLUMN_APPOINTMENT_ID)
    val appointment: AppointmentEntity?,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = UserEntity.COLUMN_USER_ID)
    val user: UserEntity,

    @Column(name = COLUMN_MISSION_ONE)
    val missionStepOneCompleteAt: Instant?,

    @Column(name = COLUMN_MISSION_TWO)
    val missionStepTwoCompleteAt: Instant?,

) {
    companion object {
        const val TABLE_APPOINTMENT_MISSION = "appointment_mission"
        const val COLUMN_MISSION_ONE = "mission_step_one_complete_at"
        const val COLUMN_MISSION_TWO = "mission_step_two_complete_at"
    }

    fun updateEntity(missionCmd: UpdateMissionStatusCommand): AppointmentMissionEntity {
        if(missionCmd.missionStep.equals(MissionCode.MISSION_STEP_ONE)){
            return AppointmentMissionEntity(
                id = this.id,
                appointment = this.appointment,
                user = this.user,
                missionStepOneCompleteAt = missionCmd.completeDateTime.toInstant(),
                missionStepTwoCompleteAt = null
            )
        }else {
            return AppointmentMissionEntity(
                id = this.id,
                appointment = this.appointment,
                user = this.user,
                missionStepOneCompleteAt = this.missionStepOneCompleteAt,
                missionStepTwoCompleteAt = missionCmd.completeDateTime.toInstant()
            )
        }
    }

}

@Embeddable
class AppointmentMissionId(
    var appointmentId: UUID,
    var userId: UUID?,
    var apntMisnId: Long?,
): Serializable
