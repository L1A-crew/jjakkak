package com.zzekak.config.exception

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
internal class ErrorController {
    @GetMapping("/exception")
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
}
