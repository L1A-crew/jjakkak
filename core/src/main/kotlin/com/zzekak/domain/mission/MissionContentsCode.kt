package com.zzekak.domain.mission

enum class MissionContentsCode(
    val code: String,
    val description: String,
) {
    MISSION_TAP("tap", "화면을 탭하여 미션 완료를 표시"), // missionContents - 미션 내용
}
