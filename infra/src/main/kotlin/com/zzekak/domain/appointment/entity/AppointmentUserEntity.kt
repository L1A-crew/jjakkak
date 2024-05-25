package com.zzekak.infra.domain.appointment.entity

import com.zzekak.infra.domain.appointment.entity.AppointmentUserEntity.Companion.TABLE_USER_ADDRESS
import com.zzekak.infra.domain.user.entity.UserEntity
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = TABLE_USER_ADDRESS)
internal class AppointmentUserEntity(
    @EmbeddedId
    val id: AppointmentUserId,
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("appointmentId")
    @JoinColumn(name = AppointmentEntity.COLUMN_APPOINTMENT_ID)
    val appointment: AppointmentEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = UserEntity.COLUMN_USER_ID)
    val user: UserEntity
) {
    companion object {
        const val TABLE_USER_ADDRESS = "appointment_user"
    }
}

@Embeddable
internal class AppointmentUserId(
    var appointmentId: UUID,
    var userId: UUID
) : Serializable
