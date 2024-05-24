package l1a.jjakkak.api

object ApiUrl {
    // Docs
    const val DOCS = "/docs"

    // HealthCheck
    const val HEALTH_CHECK = "/healthCheck"

    // User
    const val USER = "/user"
    const val USER_JOIN_OR_LOGIN = "$USER/joinOrLogin"
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
