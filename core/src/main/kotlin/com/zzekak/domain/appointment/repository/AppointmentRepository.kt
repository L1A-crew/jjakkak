package com.zzekak.domain.appointment.repository

import com.zzekak.domain.appointment.model.Appointment
import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.user.UserId
import kotlin.reflect.KClass

/**
 * 조회 모델 갯수만큼 비슷한 구현이 반복되는 것을 방지하기 위해
 * 리턴 타입을 명시하고, 해당 타입에 생성 및 반환은 Repository 에서 책임지도록 한다.
 */
interface AppointmentRepository {
    fun <T : Appointment> save(
        appointmentCommand: AppointmentCommand,
        returnType: KClass<out T>
    ): T

    fun <T : Appointment> findAllByUserId(
        userId: UserId,
        returnType: KClass<out T>
    ): List<T>

    fun <T : Appointment> findBy(
        id: AppointmentId,
        returnType: KClass<out T>
    ): T?

}
