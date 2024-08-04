package com.zzekak.domain.push.entity

import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.user.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = AppointmentPushEntity.APPOINTMENT_PUSH)
class AppointmentPushEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_APPOINTMENT_PUSH_ID)
    val appointmentPushId: Long?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = AppointmentEntity.COLUMN_APPOINTMENT_ID)
    val appointmentId: AppointmentEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserEntity.COLUMN_USER_ID)
    val userId: UserEntity,
    @Column(name = COLUMN_CREATE_AT)
    val createAt: Instant
) {
    companion object {
        const val APPOINTMENT_PUSH = "appointment_push"
        const val COLUMN_APPOINTMENT_PUSH_ID = "appointment_push_id"
        const val COLUMN_CREATE_AT = "create_at"
    }

    override fun toString(): String {
        return super.toString()
    }
}
