package com.zzekak.domain.appointment.dao

import com.zzekak.domain.appointment.entity.AppointmentEntity
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.user.UserId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface AppointmentEntityDao {
    fun save(appointmentEntity: AppointmentEntity): AppointmentEntity

    fun findById(id: AppointmentId): AppointmentEntity?

    fun findByUserId(userId: UserId): List<AppointmentEntity>
}

@Repository
interface AppointmentEntityJpaRepository : JpaRepository<AppointmentEntity, UUID> {
    fun findByAppointmentId(id: UUID): AppointmentEntity?

    fun findAllByOwnerId(userId: UUID): List<AppointmentEntity>
}

@Repository
class AppointmentDaoImpl(
    val delegate: AppointmentEntityJpaRepository
) : AppointmentEntityDao {
    override fun save(appointmentEntity: AppointmentEntity): AppointmentEntity = delegate.save(appointmentEntity)

    override fun findById(id: AppointmentId): AppointmentEntity? = delegate.findByAppointmentId(id.value)

    override fun findByUserId(userId: UserId): List<AppointmentEntity> = delegate.findAllByOwnerId(userId.value)
}
