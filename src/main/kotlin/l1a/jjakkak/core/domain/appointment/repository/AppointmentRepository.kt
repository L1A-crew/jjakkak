package l1a.jjakkak.core.domain.appointment.repository

import l1a.jjakkak.core.domain.appointment.model.AppointmentCommand
import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery

interface AppointmentRepository {
    fun save(appointmentCommand: AppointmentCommand): AppointmentQuery
}