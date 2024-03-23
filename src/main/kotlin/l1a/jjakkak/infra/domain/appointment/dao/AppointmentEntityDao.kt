package l1a.jjakkak.infra.domain.appointment.dao

import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import l1a.jjakkak.infra.domain.appointment.entity.AppointmentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface AppointmentEntityDao {
    fun save(appointmentEntity: AppointmentEntity): AppointmentEntity

    fun findById(id: AppointmentId): AppointmentEntity?
}

@Repository
interface AppointmentEntityJpaRepository : JpaRepository<AppointmentEntity, UUID> {
    fun findByAppointmentId(id: UUID): AppointmentEntity?
}

@Repository
class AppointmentDaoImpl(
    val delegate: AppointmentEntityJpaRepository
) : AppointmentEntityDao {
    override fun save(appointmentEntity: AppointmentEntity): AppointmentEntity = delegate.save(appointmentEntity)

    override fun findById(id: AppointmentId): AppointmentEntity? = delegate.findByAppointmentId(id.value)
}
