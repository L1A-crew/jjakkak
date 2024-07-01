package com.zzekak.domain.mission

/**
 * 공통 코드값
 * */
enum class MissionCode(
//    val code: String,
    val description: String,

) {
    /*mission - 미션의 개수가 늘어나도 MISSION_STEP prefix만 지키면 자동으로 미션목록에 추가됨.*/
    MISSION_STEP_ONE("1단계 미션"),
    MISSION_STEP_TWO("2단계 미션"),



}
