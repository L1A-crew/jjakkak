package com.zzekak.domain.mission

import com.zzekak.exception.ExceptionEnum
import com.zzekak.exception.ZzekakException

/**
 * 공통 코드값
 * */
enum class MissionCode(
    val code: String,
    val description: String,
) {
    // mission - 미션의 개수가 늘어나도 MISSION_STEP prefix만 지키면 자동으로 미션목록에 추가됨.
    MISSION_STEP_1("M1", "1단계 인증요쳥(출발인증)"),
    MISSION_STEP_2("M2", "2단계 인증요쳥(중간 인증)"),
    MISSION_STEP_3("M3", "3단계 인증요청(도착 인증)"),
    ;

    companion object {
        private val map = values().associateBy(MissionCode::code)

        fun fromCode(code: String): MissionCode =
            map[code] ?: throw ZzekakException(ExceptionEnum.MISSION_PHASE_CODE_NOT_EXIST)
    }
}
