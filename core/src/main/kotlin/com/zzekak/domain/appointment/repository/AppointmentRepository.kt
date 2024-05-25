package com.zzekak.core.domain.appointment.repository

import com.zzekak.core.domain.appointment.model.AppointmentCommand
import com.zzekak.core.domain.appointment.model.AppointmentQuery
import com.zzekak.core.domain.user.UserId

interface AppointmentRepository {
    fun save(appointmentCommand: AppointmentCommand): AppointmentQuery

    fun findAllByUserId(userId: UserId): List<AppointmentQuery>
}
