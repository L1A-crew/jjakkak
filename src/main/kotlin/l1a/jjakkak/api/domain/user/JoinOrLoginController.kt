package l1a.jjakkak.api.domain.user

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.user.reqeust.JoinOrLoginRequest
import l1a.jjakkak.api.domain.user.response.TokenResponse
import l1a.jjakkak.core.domain.user.usecase.JoinOrLoginUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
internal interface JoinOrLoginController {
    @PostMapping(ApiUrl.USER_JOIN_OR_LOGIN)
    fun joinOrLogin(@RequestBody request: JoinOrLoginRequest): TokenResponse
}

@RestController
internal class JoinOrLoginControllerImpl(
    val useCase: JoinOrLoginUseCase
) : JoinOrLoginController {
    override fun joinOrLogin(request: JoinOrLoginRequest): TokenResponse =
        useCase.joinOrLogin(request.toMessage()).run { TokenResponse.from(this) }
}