package com.zzekak.domain.appointment.usecase

import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.appointment.repository.AppointmentRepository
import org.springframework.stereotype.Service

@Service
class CreateAppointmentUseCase(
    val appointmentRepo: AppointmentRepository
) {
    fun createAppointment(appointment: AppointmentCommand): AppointmentQuery = appointmentRepo.save(appointment)
}
