package com.zzekak.domain.push.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = AppointmentPushDataEntity.TABLE_APPOINTMENT_PUSH_DATA)
class AppointmentPushDataEntity(
    @EmbeddedId
    val id: AppointmentPushDataId,
    @ManyToOne
    @MapsId("appointmentPushId")
    @JoinColumn(name = COLUMN_APPOINTMENT_PUSH_ID)
    val appointmentPushId: AppointmentPushEntity,
    @Column(name = COLUMN_PUSH_MESSAGE)
    val pushValue: String?,
    @Column(name = COLUMN_PUSH_DATA_VALUE)
    val pushDataValue: String?,
    @Column(name = COLUMN_SEND_AT)
    val sendAt: Boolean
) {
    companion object {
        const val TABLE_APPOINTMENT_PUSH_DATA = "appointment_push_data"
        const val COLUMN_PUSH_MESSAGE = "push_message"
        const val COLUMN_PUSH_DATA_VALUE = "push_data_value"
        const val COLUMN_SEND_AT = "send_at"
        const val COLUMN_APPOINTMENT_PUSH_ID = "appointment_push_id"
    }
}

@Embeddable
class AppointmentPushDataId(
    var appointmentPushId: Long,
    var pushType: String
) : Serializable
