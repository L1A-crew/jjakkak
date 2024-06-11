package com.zzekak.domain.appointment.repository

import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentQuery
import com.zzekak.domain.user.UserId

interface AppointmentRepository {
    fun save(appointmentCommand: AppointmentCommand): AppointmentQuery

    fun findAllByUserId(userId: UserId): List<AppointmentQuery>
}
