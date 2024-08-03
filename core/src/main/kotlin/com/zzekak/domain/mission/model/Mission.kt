package com.zzekak.domain.mission.model

import com.zzekak.domain.appointment.model.AppointmentId
import com.zzekak.domain.mission.MissionCode
import com.zzekak.domain.mission.MissionContentsCode
import com.zzekak.domain.mission.MissionContentsType
import com.zzekak.domain.user.UserId

sealed interface Mission

    data class MissionCommand(
        val missionContents: MissionContentsCode,
        val contentType: MissionContentsType,
    ) : Mission

