package com.zzekak.config.exception

import com.zzekak.exception.ZzekakException
import org.springframework.http.ResponseEntity

data class ErrorResponse(
    val code: String,
    var message: String
) {
    companion object {
        fun toResponseEntity(e: ZzekakException): ResponseEntity<ErrorResponse> =
            ResponseEntity.status(e.ex.rStatus.value())
                .body(ErrorResponse(e.ex.code, e.ex.message))
    }
}
