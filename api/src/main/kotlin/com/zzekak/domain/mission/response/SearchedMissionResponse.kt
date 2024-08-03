package com.zzekak.domain.mission.response

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.mission.model.AppointmentUserMissionQuery
import com.zzekak.domain.user.UserId
import java.time.Instant

internal data class SearchedMissionResponse(
    val missions: List<AppointmentUserMission>
) {
    companion object {
        fun from(src: Collection<AppointmentUserMissionQuery>): SearchedMissionResponse =
            SearchedMissionResponse(
                missions = src.map(AppointmentUserMission::from),
            )
    }
}

internal data class AppointmentUserMission(
    val missionId: Long,
    val appointmentId: AppointmentId,
    val userName: String,
    val userId: UserId,
    val phaseCd: MissionCode,
    val complteAt: Instant

){

    companion object{
        fun from(src: AppointmentUserMissionQuery): AppointmentUserMission =
            AppointmentUserMission(
                appointmentId = src.appointmentId,
                userName = src.userName,
                userId = src.userId,
                missionId = src.missionId,
                phaseCd = src.phaseCd,
                complteAt = src.complateAt
            )
    }
}
