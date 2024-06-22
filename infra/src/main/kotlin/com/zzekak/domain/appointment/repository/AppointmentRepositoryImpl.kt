package com.zzekak.domain.appointment.repository

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId
import com.zzekak.domain.appointment.dao.AppointmentEntityDao
import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.appointment.model.Appointment
import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.dao.UserEntityDao
import com.zzekak.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.KClass

@Repository
internal class AppointmentRepositoryImpl(
    val dao: AppointmentEntityDao,
    val userDao: UserEntityDao
) : AppointmentRepository {
    @Transactional
    override fun <T : Appointment> save(
        appointmentCommand: AppointmentCommand,
        returnType: KClass<out T>
    ): T {
        val existed = dao.findById(appointmentCommand.id)
        val existedUsers = userDao.findAllByIds(appointmentCommand.participants)

        val saved =
            dao.save(
                appointmentCommand.toEntity(
                    existed = existed,
                    participants = existedUsers,
                ),
            )

        return saved.toDomain(returnType)
    }

    @Transactional
    override fun <T : Appointment> findAllByUserId(
        userId: UserId,
        returnType: KClass<out T>
    ): List<T> = dao.findByUserId(userId).map { it.toDomain(returnType) }

    @Transactional
    override fun <T : Appointment> findBy(
        id: AppointmentId,
        returnType: KClass<out T>
    ): T? = dao.findById(id)?.toDomain(returnType)

    private fun AppointmentCommand.toEntity(
        existed: AppointmentEntity?,
        participants: Collection<UserEntity>
    ) = existed?.apply {
        this.ownerId = this@toEntity.ownerId.value
        this.name = this@toEntity.name
        this.appointmentAddress = address.toEntity(existed.appointmentAddress)
        this.appointmentTime = this@toEntity.appointmentTime
        this.participants = participants.toMutableSet()
        this.deleted = this@toEntity.deleted
    } ?: AppointmentEntity(
        appointmentId = id.value,
        ownerId = ownerId.value,
        name = name,
        appointmentAddress = address.toEntity(null),
        appointmentTime = appointmentTime,
        participants = participants.toSet(),
        deleted = deleted,
    )

    private fun AppointmentAddress.toEntity(existed: AppointmentAddressEntity?) =
        AppointmentAddressEntity(
            id = existed?.id ?: id.value,
            cityOrProvince = address.cityOrProvince,
            districtOrCity = address.districtOrCity,
            postalCode = address.postalCode,
            jibunAddress = address.jibunAddress,
            roadAddress = address.roadAddress,
            x = address.x,
            y = address.y,
        )

    @Suppress("UNCHECKED_CAST")
    private fun <T : Appointment> AppointmentEntity.toDomain(clazz: KClass<out T>): T =
        when (clazz) {
            AppointmentCommand::class ->
                AppointmentCommand(
                    id = AppointmentId(appointmentId),
                    ownerId = UserId(ownerId),
                    name = name,
                    address = appointmentAddress.toDomain(),
                    appointmentTime = appointmentTime,
                    participants = participants.map { UserId(it.userId) },
                    deleted = deleted,
                ) as T

            AppointmentQuery::class ->
                AppointmentQuery(
                    id = AppointmentId(appointmentId),
                    ownerId = UserId(ownerId),
                    name = name,
                    address = appointmentAddress.toDomain(),
                    appointmentTime = appointmentTime,
                    participants = participants.map { UserId(it.userId) },
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    deleted = deleted,
                ) as T

            else -> throw IllegalArgumentException("Unsupported type: ${clazz.qualifiedName}")
        }

    private fun AppointmentAddressEntity.toDomain() =
        AppointmentAddress.create(
            id = AppointmentAddressId(id),
            cityOrProvince = cityOrProvince,
            districtOrCity = districtOrCity,
            postalCode = postalCode,
            jibunAddress = jibunAddress,
            roadAddress = roadAddress,
            x = x,
            y = y,
            undergroundYn = "N",
            buildingName = "",
        )
}
