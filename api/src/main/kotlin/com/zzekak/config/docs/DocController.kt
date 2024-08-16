package com.zzekak.config.docs

import com.zzekak.ApiUrl
import com.zzekak.config.exception.ExceptionEnumDto
import com.zzekak.domain.mission.MissionCode
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 미션 단계에 대한 문서. MissionCodeEnum을 list로 내려준다.
 */
@RestController()
internal class DocController {
    @GetMapping("${ApiUrl.DOC}/exception")
    @Operation(summary = "Error Code 조회", description = "enum클래스에 정의된 에러 항목들을 보여준다.")
    fun viewException(): List<Map<String, Any>> =
        ExceptionEnumDto.entries.map {
                code ->
            mapOf(
                "code" to code.code,
                "status" to code.rStatus,
                "message" to code.message,
            )
        }

    @GetMapping("${ApiUrl.DOC}/missionStep")
    @Operation(summary = "Mission Code 조회", description = "enum클래스에 정의된 에러 항목들을 보여준다.")
    fun viewMissionCode(): List<Map<String, Any>> =
        MissionCode.entries.map {
                code ->
            mapOf(
                "name" to code.name,
                "code" to code.code,
                "description" to code.description,
            )
        }
}
