package com.zzekak.domain.address.model

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

// 길찾기 api 탐색 결과
// 지하철/버스 기능을 나눠야할지 상의 필요. 구글 길찾기 api는 가장 빠른 방법 하나만 보여줌.
@Serializable
internal data class PathFindResponse(
    @JsonProperty("arrival_time")
    val arrivalTime: ArrivalTime,
    @JsonProperty("departure_time")
    val departureTime: DepartureTime,
    @JsonProperty("duration")
    val duration: Duration,
    @JsonProperty("start_address")
    val startAddress: String,
    @JsonProperty("end_address")
    val endAddress: String
) {
    // jsonObject에서 필요한 객체 꺼내는 메서드
    companion object {
        fun to(jsonObject: JsonObject): PathFindResponse {
            val routes: JsonArray = jsonObject.get("routes")!!.jsonArray // routes는 빈배열로라도 내려온다.
            if (routes.isEmpty()) {
                return PathFindResponse(
                    ArrivalTime("", "", 0),
                    DepartureTime("", "", 0),
                    Duration("", 0),
                    "",
                    "",
                )
            }
            val legs: JsonArray = routes[0].jsonObject.get("legs") as JsonArray
            return parseJson(legs[0] as JsonObject)
        }

        private fun parseJson(legs: JsonObject): PathFindResponse {
            val arrivalTime = Json.decodeFromJsonElement<ArrivalTime>(legs.get("arrival_time")!!)
            val departureTime = Json.decodeFromJsonElement<DepartureTime>(legs.get("departure_time")!!)
            val duration = Json.decodeFromJsonElement<Duration>(legs.get("duration")!!)
            val endAddress = Json.decodeFromJsonElement<String>(legs.get("end_address")!!)
            val startAddress = Json.decodeFromJsonElement<String>(legs.get("start_address")!!)

            return PathFindResponse(
                arrivalTime = arrivalTime,
                departureTime = departureTime,
                duration = duration,
                endAddress = endAddress,
                startAddress = startAddress,
            )
        }
    }
}

// 도착시간
@Serializable
internal data class ArrivalTime(
    val text: String,
    @JsonProperty("time_zone")
    val time_zone: String,
    val value: Long
)

// 출발시간
@Serializable
internal data class DepartureTime(
    val text: String,
    @JsonProperty("time_zone")
    val time_zone: String,
    val value: Long
)

// 예상 소요시간. 약속시간 기준
@Serializable
internal data class Duration(
    val text: String,
    val value: Long
)
