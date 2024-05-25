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

    // Appointment
    const val APPOINTMENT = "/appointment"
    const val CREATE_APPOINTMENT = "$APPOINTMENT/create"

    // address
    const val ADDRESS = "/address"
    const val ADDRESS_SEARCH = "$ADDRESS/search"

    // path finding
    const val PATH = "/path"
    const val PATH_FINDING = "$PATH/find"

    // Exception
    const val EXCEPTION = "/exception"
}
