package com.zzekak.domain.appointmentmission.dao

import com.zzekak.domain.appointmentmission.entity.AppointmentMissionEntity
import com.zzekak.domain.appointmentmission.entity.AppointmentMissionId
import com.zzekak.domain.mission.model.AppointmentUserMissionQuery
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

interface AppointmentMissionEntityDao {
    fun save(apms: AppointmentMissionEntity): AppointmentMissionEntity

    fun <T> findByAppointmentId(value: UUID): List<T>

    fun findById(appointmentMissionId: AppointmentMissionId): AppointmentMissionEntity

    fun findByAppointmentId(value: AppointmentMissionId): AppointmentMissionEntity

    fun getNotDepartureUserList(): List<String>
}

@Repository
interface AppointmentMissionDaoJpaRepository : JpaRepository<AppointmentMissionEntity, AppointmentMissionId> {
    override fun findById(appointmentMissionId: AppointmentMissionId): Optional<AppointmentMissionEntity>

    @Query(
        value = """
        select 	u.fcm_key
        from appointment_push ap
        inner join appointment_mission am on am.appointment_id = ap.appointment_id
        inner join (
            select  apd.appointment_push_id,
                max(CASE WHEN push_type = 'DEPARTURE_TIME' THEN STR_TO_DATE(push_data_value, '%Y-%m-%dT%H:%i:%sZ') end) AS departure_time,
                max(CASE WHEN push_type = 'ARRIVAL_TIME' THEN STR_TO_DATE(push_data_value, '%Y-%m-%dT%H:%i:%sZ') end) AS arrival_time
            from appointment_push_data apd
            group by apd.appointment_push_id
        ) apd
        inner join `user` u on ap.user_id = u.user_id
        where am.phase_cd = 'M1'
        and am.complete_at is null
        and apd.appointment_push_id = ap.appointment_push_id
        and now() >= date_add(apd.departure_time, interval timestampdiff(second, apd.departure_time, apd.arrival_time) * 0.3 second)
    """,
        nativeQuery = true,
    )
    fun findAllNotDepartureUsers(): List<String>
}

@Repository
class AppointmentMissionEntityDaoImpl(
    val repo: AppointmentMissionDaoJpaRepository,
    @PersistenceContext
    val entityManager: EntityManager
) : AppointmentMissionEntityDao {
    override fun save(apms: AppointmentMissionEntity): AppointmentMissionEntity = repo.save(apms)

    override fun <T> findByAppointmentId(appointmentId: UUID): List<T> {
        val jpql =
            """
            SELECT new com.zzekak.domain.mission.model.AppointmentUserMissionQuery(
                am.mission.missionId,
                am.appointment.appointmentId,
                u.name,
                u.userId,
                am.phaseCd,
                am.completeAt
            )
            FROM AppointmentMissionEntity am
            JOIN UserEntity u ON am.user = u
            JOIN MissionEntity m ON am.mission = m
            WHERE am.appointment.appointmentId = :appointmentId
            """.trimIndent()

        val query = entityManager.createQuery(jpql, AppointmentUserMissionQuery::class.java)
        query.setParameter("appointmentId", appointmentId)

        return query.resultList as List<T>
    }

    override fun findByAppointmentId(value: AppointmentMissionId): AppointmentMissionEntity = repo.findById(value).get()

    override fun findById(appointmentMissionId: AppointmentMissionId): AppointmentMissionEntity =
        repo.findById(
            appointmentMissionId,
        ).get()

    override fun getNotDepartureUserList(): List<String> = repo.findAllNotDepartureUsers()
}
