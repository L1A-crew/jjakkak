package com.zzekak.domain.push

/*
* push 보내는 유형
* PUSH_TYPE prefix로 push_data 의 push_type에 값이 들어감.
* */
enum class PushTypeCode(
    val code: String,
    val description: String,
) {
    PUSH_TYPE_DEPARTURE_TIME("DEPARTURE_TIME", "최소출발시간"),
    PUSH_TYPE_ARRIVAL_TIME("ARRIVAL_TIME", "도착 예상 시간"),
    PUSH_TYPE_RADIUS_2KM("RADIUS_2KM", "2KM 반경 내 위치여부"),
}
