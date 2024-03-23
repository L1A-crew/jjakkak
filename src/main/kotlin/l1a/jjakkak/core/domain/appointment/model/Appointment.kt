package l1a.jjakkak.core.domain.appointment.model

import l1a.jjakkak.core.domain.address.model.IdentifierAddress
import l1a.jjakkak.core.domain.common.IdTypeUUID
import l1a.jjakkak.core.domain.user.UserId
import java.time.Instant
import java.util.*

@JvmInline
value class AppointmentId(override val value: UUID) : IdTypeUUID

interface AppointmentCommand {
    val id: AppointmentId
    val ownerId: UserId
    val name: String
    val address: IdentifierAddress
    val appointmentTime: Instant
    val participants: List<UserId>
    val deleted: Boolean

    companion object {
        fun create(
            id: AppointmentId,
            ownerId: UserId,
            name: String,
            address: IdentifierAddress,
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
                deleted = deleted
            )
    }
}

data class AppointmentCommandImpl(
    override val id: AppointmentId,
    override val ownerId: UserId,
    override val name: String,
    override val address: IdentifierAddress,
    override val appointmentTime: Instant,
    override val participants: List<UserId>,
    override val deleted: Boolean
) : AppointmentCommand

interface AppointmentQuery : AppointmentCommand {
    override val id: AppointmentId
    override val ownerId: UserId
    override val name: String
    override val address: IdentifierAddress
    override val appointmentTime: Instant
    override val participants: List<UserId>
    val createdAt: Instant
    val updatedAt: Instant

    companion object {
        fun create(
            id: AppointmentId,
            ownerId: UserId,
            name: String,
            address: IdentifierAddress,
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
                deleted = deleted
            )
    }
}

data class AppointmentQueryImpl(
    override val id: AppointmentId,
    override val ownerId: UserId,
    override val name: String,
    override val address: IdentifierAddress,
    override val appointmentTime: Instant,
    override val createdAt: Instant,
    override val updatedAt: Instant,
    override val participants: List<UserId>,
    override val deleted: Boolean
) : AppointmentQuery