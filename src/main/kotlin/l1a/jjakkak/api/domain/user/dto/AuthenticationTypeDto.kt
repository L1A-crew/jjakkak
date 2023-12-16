package l1a.jjakkak.api.domain.user.dto

import com.fasterxml.jackson.annotation.JsonValue
import l1a.jjakkak.core.domain.auth.AuthenticationType


enum class AuthenticationTypeDto(@JsonValue val code: String) {
    KAKAO(code = "ka"),
    APPLE(code = "ap");

    companion object {
        fun from(domainType: AuthenticationType) =
            when(domainType){
                AuthenticationType.KAKAO -> KAKAO
                AuthenticationType.APPLE -> APPLE
            }
    }
}