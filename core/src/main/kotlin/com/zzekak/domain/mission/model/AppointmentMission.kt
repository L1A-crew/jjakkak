package com.zzekak.domain.mission.model

import com.zzekak.domain.appointment.model.AppointmentCommand
import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.user.UserId
import java.time.Instant
import java.time.ZonedDateTime
import java.util.*


sealed interface AppointmentMission


data class AppointmentMissionCommand (
    val appointmentId: AppointmentId,
    val userId: UserId,
    val missionStepOneCompleteAt: Instant?,
    val missionStepTwoCompleteAt: Instant?,
): AppointmentMission


data class AppointmentUserMissionQuery(
    val appointmentMissionId: Long,
    val appointmentId: AppointmentId,
    val userName: String,
    val missionStepOneComplteYn: String,
    val missionStepTwoComplteYn: String,
    val missionStepOneCompleteTime: Instant?,
    val missionStepTwoComplateTime: Instant?,
    val userId: UserId
): AppointmentMission


data class UpdateMissionStatusCommand(
    val appointmentMissionId: Long,
    val appointmentId: AppointmentId,
    val userId: UserId,
    val missionStep: MissionCode,
    val completeDateTime: ZonedDateTime
)
