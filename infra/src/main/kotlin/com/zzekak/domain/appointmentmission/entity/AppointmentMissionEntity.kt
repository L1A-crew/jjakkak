package com.zzekak.domain.appointmentmission.entity

import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity.Companion.TABLE_APPOINTMENT_MISSION
import com.zzekak.domain.mission.entity.MissionEntity
import com.zzekak.domain.user.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
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
    @MapsId("missionId")
    @OneToOne
    @JoinColumn(name = MissionEntity.COLUMN_MISSION_ID)
    val mission: MissionEntity,
    @Column(name = COLUMN_PHASE_CD)
    val phaseCd: String,
    @Column(name = COLUMN_COMPLTE_AT)
    val completeAt: Instant?
) {
    companion object {
        const val TABLE_APPOINTMENT_MISSION = "appointment_mission"
        const val COLUMN_PHASE_CD = "phase_cd"
        const val COLUMN_COMPLTE_AT = "complete_at"
    }
}

@Embeddable
class AppointmentMissionId(
    var appointmentId: UUID,
    var userId: UUID?,
    var missionId: Long?,
) : Serializable
