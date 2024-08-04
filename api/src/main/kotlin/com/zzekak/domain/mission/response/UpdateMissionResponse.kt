package com.zzekak.domain.mission.response

import com.zzekak.domain.mission.model.AppointmentMission
import com.zzekak.domain.mission.model.AppointmentUserMissionQuery

class UpdateMissionResponse (
    val appointmentMission: AppointmentMission
) {
    companion object {
        fun from(src: AppointmentUserMissionQuery): UpdateMissionResponse =
            UpdateMissionResponse(
                appointmentMission = src
            )
    }
}
