package l1a.jjakkak.api.domain.user

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.user.reqeust.UserCreateRequest
import l1a.jjakkak.api.domain.user.response.UserCreateResponse
import l1a.jjakkak.core.domain.auth.AuthenticationType
import l1a.jjakkak.core.domain.user.UserQuery
import l1a.jjakkak.core.domain.user.message.CreateUserMessage
import l1a.jjakkak.core.domain.user.usecase.CreateUserUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
interface CreateUserController {
    @PostMapping(ApiUrl.USER_CREATE)
    fun createUser(@RequestBody request: UserCreateRequest): UserCreateResponse
}

@RestController
class CreateUserControllerImpl(
    val userUseCase: CreateUserUseCase
) : CreateUserController {
    override fun createUser(request: UserCreateRequest): UserCreateResponse =
        userUseCase
            .createUser(request.toMessage())
            .run { UserCreateResponse.from(this) }
}