package com.zzekak.domain.push

enum class AppointmentPushMessage(
    val code: String,
    val description: String,
    val message: String,
) {
    DEPARTURE_TIME("DEPARTURE_TIME", "최소 출발시간", "아! 지금 출발 해야 제시간에 도착할 수 있어!"),
    NOT_DEPARTED("LATE_DEPARTURE", "출발시간 늦음", "아! 지각 위기다! 당장 출발해 보자!"),
    NOT_ARRIVAL("NOT_ARRIVAL", "약속시간 10분전에도 도착하지 못함", "아! 아직도 도착하지 못한 거야?"),
    ARRIVAL_TIME("ARRIVAL_TIME", "도착예정시간", "곧 도착이지? 도착하고 도착인증 잊지 말아줘!"),
    PUSH_TYPE_MIDL_TIME("PUSH_TYPE_MIDL_TIME", "중간진행인증", "잘 가고 있어? 가고 있다면 인증버튼 눌러줘!"),
}
