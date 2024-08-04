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
                missions = AppointmentUserMission.from(src),
            )
    }
}

internal data class MissionInfo(
    val missionId: Long,
    val appointmentId: AppointmentId,
    val phaseCd: MissionCode,
    val completeTime: Instant?,
    val completeAt: String
)
internal data class AppointmentUserMission(
    val userName: String,
    val userId: UserId,
    val missionInfo: List<MissionInfo>

){

    companion object{
        fun from(src: Collection<AppointmentUserMissionQuery>): List<AppointmentUserMission> {
            var list = src.groupBy { (it.userId to it.userName) }.mapValues {
                it.value.map { info -> MissionInfo(
                    appointmentId = info.appointmentId,
                    missionId = info.missionId,
                    phaseCd = MissionCode.fromCode(info.phaseCd),
                    completeTime = info.complateAt,
                    completeAt = if(info.complateAt != null){"Y"}else{"N"}
                ) }
            }
            val result = list.map {
                AppointmentUserMission(
                    userId = it.key.first,
                    userName = it.key.second,
                    missionInfo = it.value
                )
            }
            return result
        }




    }
}
