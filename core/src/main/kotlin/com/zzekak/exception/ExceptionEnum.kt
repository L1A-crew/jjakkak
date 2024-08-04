/*
 * Zzekak
 * Sir.LOIN Intellectual property. All rights reserved.
 */
package com.zzekak.exception

import org.springframework.http.HttpStatus

/**
 * @since 2024-05-11
 */
enum class ExceptionEnum(
    val code: String,
    val rStatus: HttpStatus,
    val message: String
) {
    // 유저 관련 Erorr => U-...
    ILLEGAL_TOKEN(" U-001", HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),

    NO_EXIST_USER("U-002", HttpStatus.UNAUTHORIZED, "존재하지 않는 사용자 입니다."),

    // server관련 Erorr => S-...
    SERVER_ERROR("S-001", HttpStatus.INTERNAL_SERVER_ERROR, "처리 중 오류가 발생했습니다."),

    //mission 관련 Error => M-..
    MISSION_PHASE_CODE_NOT_EXIST("M-001", HttpStatus.INTERNAL_SERVER_ERROR, "확인되지 않는 미션 ID입니다.")
}
