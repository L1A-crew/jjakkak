package com.zzekak.core.domain.appointment.usecase

import com.zzekak.core.domain.appointment.model.AppointmentQuery
import com.zzekak.core.domain.appointment.repository.AppointmentRepository
import com.zzekak.core.domain.user.UserId
import org.springframework.stereotype.Service

@Service
class FindAppointmentUseCase(
    val appointmentRepository: AppointmentRepository
) {
    fun findAll(userId: UserId): List<AppointmentQuery> = appointmentRepository.findAllByUserId(userId)
}
