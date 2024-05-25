package com.zzekak.domain.address

import com.zzekak.ApiUrl
import com.zzekak.domain.address.response.FindPathResponse
import com.zzekak.domain.address.usecase.PathFindingUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

/**
 * 길찾기 API 연동
 * */
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface PathFindingController {
    @Operation(summary = "길찾기 API- 소요시간 조회", description = "약속시간을 기준으로 그 이전에 도착하는 초단시간을 탐색함. 대중교통 기준.")
    @GetMapping(ApiUrl.PATH_FINDING)
    fun findAddress(
        @Schema(description = "출발지 x좌표")
        @RequestParam(name = "strtLocX") strtLocX: String,
        @Schema(description = "출발지 y좌표")
        @RequestParam(name = "strtLocY") strtLocY: String,
        @Schema(description = "도착지 x좌표")
        @RequestParam(name = "endLocX") endLocX: String,
        @Schema(description = "도착지 y좌표")
        @RequestParam(name = "endLocY") endLocY: String,
        @Schema(description = "약속 시간(도착시간)")
        @RequestParam(name = "appointmentTime") appointmentTime: ZonedDateTime
    ): FindPathResponse
}

@RestController
internal class PathFindingControllerImpl(
    private val pathFindingUsecase: PathFindingUseCase
) : PathFindingController {
    override fun findAddress(
        strtLocX: String,
        strtLocY: String,
        endLocX: String,
        endLocY: String,
        appointmentTime: ZonedDateTime
    ): FindPathResponse =
        pathFindingUsecase.findPath(
            strtLocX,
            strtLocY,
            endLocX,
            endLocY,
            appointmentTime,
        ).run {
            FindPathResponse.from(this)
        }
}
