package l1a.jjakkak.core.domain.auth

enum class AuthenticationType(val code: String) {
    KAKAO(code = "ka"),
    APPLE(code = "ap");

    companion object {
        fun from(code: String): AuthenticationType =
            AuthenticationType.values().firstOrNull { it.code == code }
                ?: throw IllegalArgumentException("$code is illegal Argument [AuthenticationType]")
    }
}