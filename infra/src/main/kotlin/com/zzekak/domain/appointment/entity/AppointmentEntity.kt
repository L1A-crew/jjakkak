package com.zzekak.infra.domain.appointment.entity

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.infra.domain.appointment.entity.AppointmentEntity.Companion.TABLE_APPOINTMENT
import com.zzekak.infra.domain.user.entity.UserEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_APPOINTMENT)
@EntityListeners(AuditingEntityListener::class)
class AppointmentEntity(
    @Id @Column(name = COLUMN_APPOINTMENT_ID)
    val appointmentId: UUID,
    @Column(name = COLUMN_OWNER_ID)
    val ownerId: UUID,
    @Column(name = COLUMN_NAME)
    val name: String,
    @JoinColumn(name = COLUMN_APPOINTMENT_ADDRESS_ID)
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    val appointmentAddress: AppointmentAddressEntity,
    @Column(name = COLUMN_APPOINTMENT_TIME)
    val appointmentTime: Instant,
    @ManyToMany
    @JoinTable(
        name = AppointmentUserEntity.TABLE_USER_ADDRESS,
        joinColumns = [JoinColumn(name = "appointment_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")],
    )
    val participants: Set<UserEntity> = mutableSetOf(),
    @Column(name = COLUMN_DELETED)
    val deleted: Boolean
) {
    @Column(name = COLUMN_CREATED_AT)
    @CreatedDate
    lateinit var createdAt: Instant

    @Column(name = COLUMN_UPDATED_AT)
    @LastModifiedDate
    lateinit var updatedAt: Instant

    companion object {
        const val TABLE_APPOINTMENT = "appointment"
        const val COLUMN_APPOINTMENT_ID = "appointment_id"
        const val COLUMN_OWNER_ID = "owner_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_APPOINTMENT_ADDRESS_ID = "appointment_address_id"
        const val COLUMN_APPOINTMENT_TIME = "appointment_time"
        const val COLUMN_DELETED = "deleted"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_UPDATED_AT = "updated_at"
    }
}
