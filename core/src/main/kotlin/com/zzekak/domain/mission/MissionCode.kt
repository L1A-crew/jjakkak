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
    /*mission - 미션의 개수가 늘어나도 MISSION_STEP prefix만 지키면 자동으로 미션목록에 추가됨.*/
    MISSION_STEP_ONE("M1","1단계 미션"),
    MISSION_STEP_TWO("M2","2단계 미션"),



    ;
    companion object {
        private val map = values().associateBy(MissionCode::code)
        fun fromCode(code: String): MissionCode = map[code]?: throw ZzekakException(ExceptionEnum.MISSION_PHASE_CODE_NOT_EXIST)
    }

}
