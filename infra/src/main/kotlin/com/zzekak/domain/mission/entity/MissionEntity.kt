package com.zzekak.domain.mission.entity

import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = MissionEntity.TABLE_MISSION)
@EntityListeners(AuditingEntityListener::class)
class MissionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_MISSION_ID)
    val missionId: Long?,

    @Column(name = COLUMN_MISSION_CONTENTS)
    val missionContents: String,

    @Column(name = COLUMN_CONTENT_TYPE)
    val contentType: String,

    @Column(name = COLUMN_CREATE_AT)
    val createAt: Instant
) {
    companion object {
        const val COLUMN_MISSION_ID = "mission_id"
        const val COLUMN_MISSION_CONTENTS = "mission_contents"
        const val COLUMN_CONTENT_TYPE = "content_type"
        const val COLUMN_CREATE_AT = "create_at"
        const val TABLE_MISSION = "mission"
    }
}
