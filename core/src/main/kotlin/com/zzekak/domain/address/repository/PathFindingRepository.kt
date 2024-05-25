package com.zzekak.domain.address.repository

import l1a.jjakkak.core.domain.address.model.SearchedPathResponse
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
interface PathFindingRepository {
    // 길찾기(경로탐색)
    fun findPath(
        strtLocX: String,
        strtLocY: String,
        endLocX: String,
        endLocY: String,
        appointmentTime: ZonedDateTime
    ): SearchedPathResponse
}
