package com.zzekak.domain.push.dao

import com.zzekak.domain.push.entity.AppointmentPushEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface AppointmentPushEntityDao {
    fun save(push: AppointmentPushEntity): AppointmentPushEntity
}

@Repository
class AppointmentPushEntityDaoImpl(
    val repo: AppointmentPushDataDaoJPARepository,
    @PersistenceContext
    val entityManager: EntityManager
) : AppointmentPushEntityDao {
    override fun save(push: AppointmentPushEntity): AppointmentPushEntity = repo.save(push)
}

@Repository
interface AppointmentPushDataDaoJPARepository : JpaRepository<AppointmentPushEntity, Long>
