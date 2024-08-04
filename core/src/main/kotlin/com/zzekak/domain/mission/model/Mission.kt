package com.zzekak.domain.mission.model

import com.zzekak.domain.mission.MissionContentsCode
import com.zzekak.domain.mission.MissionContentsType

sealed interface Mission

data class MissionCommand(
    val missionContents: MissionContentsCode,
    val contentType: MissionContentsType,
) : Mission
