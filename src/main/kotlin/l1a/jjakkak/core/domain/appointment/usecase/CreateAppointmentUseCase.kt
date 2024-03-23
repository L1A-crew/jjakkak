package l1a.jjakkak.core.domain.appointment.usecase

import l1a.jjakkak.core.domain.appointment.model.AppointmentCommand
import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery
import l1a.jjakkak.core.domain.appointment.repository.AppointmentRepository
import org.springframework.stereotype.Service

@Service
class CreateAppointmentUseCase(
    val appointmentRepo: AppointmentRepository
) {
    fun createAppointment(appointment: AppointmentCommand): AppointmentQuery = appointmentRepo.save(appointment)
}