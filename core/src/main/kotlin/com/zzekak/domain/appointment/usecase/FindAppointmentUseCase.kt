package com.zzekak.domain.appointment.usecase

import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.appointment.repository.AppointmentRepository
import com.zzekak.domain.user.UserId
import org.springframework.stereotype.Service

@Service
class FindAppointmentUseCase(
    val appointmentRepository: AppointmentRepository
) {
    fun findAll(userId: UserId): List<AppointmentQuery> = appointmentRepository.findAllByUserId(userId)
}
