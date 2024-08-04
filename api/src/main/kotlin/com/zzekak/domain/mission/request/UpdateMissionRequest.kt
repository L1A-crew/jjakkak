package com.zzekak.domain.mission.request

import com.zzekak.domain.mission.MissionCode
import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime
import java.util.UUID

internal data class UpdateMissionRequest(
    @Schema(description = "약속ID")
    val appointmentId: UUID,
    @Schema(description = "유저ID")
    val userId: UUID,
    @Schema(description = "미션 단계")
    val missionStep: MissionCode,
    @Schema(description = "미션 완료 시간")
    val completeDateTime: ZonedDateTime,
    @Schema(description = "미션ID")
    val missionId: Long

)
