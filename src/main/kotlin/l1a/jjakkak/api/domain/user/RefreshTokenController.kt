package l1a.jjakkak.api.domain.user

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.user.reqeust.RefreshTokenRequest
import l1a.jjakkak.api.domain.user.response.TokenResponse
import l1a.jjakkak.core.domain.user.usecase.TokenRefreshUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
internal interface RefreshTokenController {
    @PostMapping(ApiUrl.USER_TOKEN_REFRESH)
    fun refresh(@RequestBody request: RefreshTokenRequest): TokenResponse
}

@RestController
internal class RefreshTokenControllerImpl(
    val useCase: TokenRefreshUseCase
): RefreshTokenController {
    override fun refresh(request: RefreshTokenRequest): TokenResponse =
        useCase.refresh(request.refreshToken)
            .run { TokenResponse.from(this) }
}