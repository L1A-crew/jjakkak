package com.zzekak.domain.push

enum class AppointmentPushMessage (
    val code: String,
    val description: String,
    val message: String
) {
    DEPARTURE_TIME("DEPARTURE_TIME", "최소 출발시간", "아! 지금 출발 해야 제시간에 도착할 수 있어!"),
    NOT_DEPARTED("LATE_DEPARTURE", "출발시간 늦음", "아! 지각 위기다! 당장 출발해 보자!"),
    NOT_ARRIVAL("NOT_ARRIVAL", "약속시간 10분전에도 도착하지 못함", "아! 아직도 도착하지 못한 거야?"),
    RADIUS_2KM("RADIUS_2KM_IN", "약속 장소 반경 2KM내 진입","얼마 남지 않았어! 오늘 약속 시간 안에 도착해보자!"),
    NOT_RADIUS_2KM("NOT_RADIUS_2KM_IN", "약속 시간 30분 전에도 약속 장소 반경 2KM 내 진입 못함", "다른 친구들은 열심히 약속장소까지 가고 있을거야!")



}
