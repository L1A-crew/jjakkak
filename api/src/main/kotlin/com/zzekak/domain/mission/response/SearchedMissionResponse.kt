package com.zzekak.domain.mission.response

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.model.AppointmentUserMissionQuery
import com.zzekak.domain.user.UserId
import java.time.Instant

internal data class SearchedMissionResponse (
    val missions: List<AppointmentUserMission>
){
    companion object {
        fun from(src: Collection<AppointmentUserMissionQuery>): SearchedMissionResponse =
            SearchedMissionResponse(
                missions = src.map(AppointmentUserMission::from)
            )
    }

}

internal data class AppointmentUserMission(
    val appointmentMissionId: Long,
    val appointmentId: AppointmentId,
    val userName: String,
    val missionStepOneComplteYn: String,
    val missionStepTwoComplteYn: String,
    val missionStepOneCompleteTime: Instant?,
    val missionStepTwoComplateTime: Instant?,
    val userId: UserId

){
    companion object{
        fun from(src: AppointmentUserMissionQuery): AppointmentUserMission =
            AppointmentUserMission(
                appointmentMissionId = src.appointmentMissionId,
                appointmentId = src.appointmentId,
                userName = src.userName,
                missionStepOneComplteYn = src.missionStepOneComplteYn,
                missionStepTwoComplteYn = src.missionStepTwoComplteYn,
                missionStepOneCompleteTime = src.missionStepOneCompleteTime,
                missionStepTwoComplateTime = src.missionStepTwoComplateTime,
                userId = src.userId
            )
    }
}
