package com.zzekak.domain.push.dao

import com.zzekak.domain.push.entity.AppointmentPushDataEntity
import com.zzekak.domain.push.entity.AppointmentPushDataId
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface AppointmentPushDataEntityDao {
    fun save(data: AppointmentPushDataEntity): AppointmentPushDataEntity
}

@Repository
class AppointmentPushDataEntityDaoImpl(
    val repo: AppointmentPushDataEntityDaoJPARepository,
    @PersistenceContext
    val entityManager: EntityManager
) : AppointmentPushDataEntityDao {
    override fun save(push: AppointmentPushDataEntity): AppointmentPushDataEntity = repo.save(push)
}

@Repository
interface AppointmentPushDataEntityDaoJPARepository : JpaRepository<AppointmentPushDataEntity, AppointmentPushDataId>
