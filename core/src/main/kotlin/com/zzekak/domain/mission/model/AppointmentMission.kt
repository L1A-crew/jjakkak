package com.zzekak.domain.mission.model

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.user.UserId
import java.time.Instant
import java.time.ZonedDateTime

sealed interface AppointmentMission

data class AppointmentMissionCommand(
    val appointmentId: AppointmentId,
    val userId: UserId,
    val missionId: Long,
    val phaseCd: MissionCode,
    val completeAt: Instant?
): AppointmentMission


data class AppointmentUserMissionQuery(
    val missionId: Long,
    val appointmentId: AppointmentId,
    val userName: String,
    val userId: UserId,
    val phaseCd: String,
    val complateAt: Instant?
): AppointmentMission


data class UpdateMissionStatusCommand(
    val appointmentId: AppointmentId,
    val userId: UserId,
    val missionStep: MissionCode,
    val missionId: Long,
    val completeDateTime: ZonedDateTime
)
