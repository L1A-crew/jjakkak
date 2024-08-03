package com.zzekak.domain.mission.dao

import com.zzekak.domain.appointmentmission.dao.AppointmentMissionDaoJpaRepository
import com.zzekak.domain.mission.entity.MissionEntity
import com.zzekak.domain.mission.model.Mission
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface MissionEntityDao {
    fun save(mission: MissionEntity): MissionEntity
}

@Repository
interface MissionDaoJpaRepository: JpaRepository<MissionEntity, Long> {

}
@Repository
class MissionEntityDaoImpl(
    val repo: MissionDaoJpaRepository,
    @PersistenceContext
    val entityManager: EntityManager
): MissionEntityDao {
    override fun save(mission: MissionEntity): MissionEntity =
        repo.save(mission)
}
