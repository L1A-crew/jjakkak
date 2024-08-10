package com.zzekak

object ApiUrl {
    // Docs
    const val DOCS = "/docs"

    // HealthCheck
    const val HEALTH_CHECK = "/health_check"

    // User
    const val USER = "/user"
    const val USER_JOIN_OR_LOGIN = "$USER/join_or_login"
    const val USER_WITHDRAWAL = "$USER/withdrawal"
    const val USER_TOKEN_REFRESH = "$USER/token/refresh"
    const val USER_UPDATE = "$USER/update"
    const val USER_UPDATE_PROFILE_IMAGE = "$USER_UPDATE/profile_image"
    const val USER_UPDATE_FCM_KEY = "$USER_UPDATE/fcmkey"

    // Appointment
    const val APPOINTMENT = "/appointment"
    const val CREATE_APPOINTMENT = "$APPOINTMENT/create"
    const val JOIN_APPOINTMENT = "$APPOINTMENT/{${PathVariable.APPOINTMENT}}/join"

    // address
    const val ADDRESS = "/address"
    const val ADDRESS_SEARCH = "$ADDRESS/search"

    // path finding
    const val PATH = "/path"
    const val PATH_FINDING = "$PATH/find"

    // mission
    const val MISSION = "/mission"
    const val SEARCH_APPOINT_MISSION = "$MISSION/search/{${PathVariable.APPOINTMENT}}"
    const val UPDATE_MISSION = "$MISSION/update"

    // Exception
    const val EXCEPTION = "/exception"
}
