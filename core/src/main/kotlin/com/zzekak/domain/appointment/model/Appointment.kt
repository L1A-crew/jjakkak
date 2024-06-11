package com.zzekak.domain.appointment.model

import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.common.IdTypeUUID
import com.zzekak.domain.user.UserId
import java.time.Instant
import java.util.UUID

@JvmInline
value class AppointmentId(override val value: UUID) : IdTypeUUID

interface AppointmentCommand {
    val id: AppointmentId
    val ownerId: UserId
    val name: String
    val address: AppointmentAddress
    val appointmentTime: Instant
    val participants: List<UserId>
    val deleted: Boolean

    companion object {
        fun create(
            id: AppointmentId,
            ownerId: UserId,
            name: String,
            address: AppointmentAddress,
            appointmentTime: Instant,
            participants: Collection<UserId>,
            deleted: Boolean
        ): AppointmentCommand =
            AppointmentCommandImpl(
                id = id,
                ownerId = ownerId,
                name = name,
                address = address,
                appointmentTime = appointmentTime,
                participants = participants.toList(),
                deleted = deleted,
            )
    }
}

data class AppointmentCommandImpl(
    override val id: AppointmentId,
    override val ownerId: UserId,
    override val name: String,
    override val address: AppointmentAddress,
    override val appointmentTime: Instant,
    override val participants: List<UserId>,
    override val deleted: Boolean
) : AppointmentCommand

interface AppointmentQuery : AppointmentCommand {
    override val id: AppointmentId
    override val ownerId: UserId
    override val name: String
    override val address: AppointmentAddress
    override val appointmentTime: Instant
    override val participants: List<UserId>
    val createdAt: Instant
    val updatedAt: Instant

    companion object {
        fun create(
            id: AppointmentId,
            ownerId: UserId,
            name: String,
            address: AppointmentAddress,
            appointmentTime: Instant,
            participants: List<UserId>,
            createdAt: Instant,
            updatedAt: Instant,
            deleted: Boolean
        ): AppointmentQuery =
            AppointmentQueryImpl(
                id = id,
                ownerId,
                name = name,
                address = address,
                appointmentTime = appointmentTime,
                participants = participants,
                createdAt = createdAt,
                updatedAt = updatedAt,
                deleted = deleted,
            )
    }
}

data class AppointmentQueryImpl(
    override val id: AppointmentId,
    override val ownerId: UserId,
    override val name: String,
    override val address: AppointmentAddress,
    override val appointmentTime: Instant,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val participants: List<UserId>,
    override val deleted: Boolean
) : AppointmentQuery
