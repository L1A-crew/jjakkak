package com.zzekak.infra.domain.appointment.repository

import com.zzekak.domain.address.entity.AppointmentAddressEntity
import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId
import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.appointment.repository.AppointmentRepository
import com.zzekak.domain.user.UserId
import com.zzekak.infra.domain.appointment.dao.AppointmentEntityDao
import com.zzekak.infra.domain.appointment.entity.AppointmentEntity
import com.zzekak.infra.domain.user.dao.UserEntityDao
import com.zzekak.infra.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class AppointmentRepositoryImpl(
    val dao: AppointmentEntityDao,
    val userDao: UserEntityDao
) : AppointmentRepository {
    @Transactional
    override fun save(appointmentCommand: AppointmentCommand): AppointmentQuery {
        val existed = dao.findById(appointmentCommand.id)
        val existedUsers = userDao.findAllByIds(appointmentCommand.participants)

        val saved =
            dao.save(
                appointmentCommand.toEntity(
                    existed = existed,
                    participants = existedUsers,
                ),
            )

        return saved.toDomain()
    }

    override fun findAllByUserId(userId: UserId): List<AppointmentQuery> =
        dao.findByUserId(userId).map { it.toDomain() }

    private fun AppointmentCommand.toEntity(
        existed: AppointmentEntity?,
        participants: Collection<UserEntity>
    ) = AppointmentEntity(
        appointmentId = id.value,
        ownerId = ownerId.value,
        name = name,
        appointmentAddress = address.toEntity(existed?.appointmentAddress),
        appointmentTime = appointmentTime,
        participants = participants.toSet(),
        deleted = false,
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

    private fun AppointmentEntity.toDomain() =
        AppointmentQuery.create(
            id = AppointmentId(appointmentId),
            ownerId = UserId(ownerId),
            name = name,
            address = appointmentAddress.toDomain(),
            appointmentTime = appointmentTime,
            participants = participants.map { UserId(it.userId) },
            createdAt = createdAt,
            updatedAt = updatedAt,
            deleted = deleted,
        )

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
