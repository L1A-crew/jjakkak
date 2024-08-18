package com.zzekak.domain.push.dao

import com.zzekak.domain.push.batch.model.BatchPushDataQuery
import com.zzekak.domain.push.entity.AppointmentPushDataEntity
import com.zzekak.domain.push.entity.AppointmentPushDataId
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

interface AppointmentPushDataEntityDao {
    fun save(data: AppointmentPushDataEntity): AppointmentPushDataEntity

    fun getAllPushList(time: String): List<BatchPushDataQuery>

    fun updatePushSendAt(pushList: List<AppointmentPushDataId>)
}

@Repository
class AppointmentPushDataEntityDaoImpl(
    val repo: AppointmentPushDataEntityDaoJPARepository,
    @PersistenceContext
    val entityManager: EntityManager
) : AppointmentPushDataEntityDao {
    override fun save(push: AppointmentPushDataEntity): AppointmentPushDataEntity = repo.save(push)

    override fun getAllPushList(time: String): List<BatchPushDataQuery> = repo.findAllBySendAt(false, time)

    override fun updatePushSendAt(pushList: List<AppointmentPushDataId>) = repo.updatePushSendAt(pushList)
}

@Repository
interface AppointmentPushDataEntityDaoJPARepository : JpaRepository<AppointmentPushDataEntity, AppointmentPushDataId> {
    @Query(
        value = """
            SELECT new com.zzekak.domain.push.batch.model.BatchPushDataQuery(u, a)
            FROM AppointmentPushDataEntity a
            JOIN a.appointmentPushId b
            JOIN b.userId u
            WHERE a.sendAt = :sendAt
            AND FUNCTION('STR_TO_DATE', a.pushDataValue, '%Y-%m-%dT%H:%i:%sZ')
                BETWEEN FUNCTION('NOW')
                AND FUNCTION('STR_TO_DATE', FUNCTION('SUBSTRING', :time, 1, 19) , '%Y-%m-%dT%H:%i:%sZ')
        """,
    )
    fun findAllBySendAt(
        sendAt: Boolean,
        time: String
    ): List<BatchPushDataQuery>

    @Query(
        """
        update AppointmentPushDataEntity a
        set a.sendAt = true
        where a.id in :pushList
    """,
    )
    fun updatePushSendAt(pushList: List<AppointmentPushDataId>)
}
