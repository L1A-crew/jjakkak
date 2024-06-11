package com.zzekak.domain.user

import com.zzekak.ApiUrl
import com.zzekak.domain.user.reqeust.JoinOrLoginRequest
import com.zzekak.domain.user.response.JoinOrLoginResponse
import com.zzekak.domain.user.usecase.JoinOrLoginUseCase
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface JoinOrLoginController {
    @PostMapping(ApiUrl.USER_JOIN_OR_LOGIN)
    fun joinOrLogin(
        @RequestBody request: JoinOrLoginRequest
    ): JoinOrLoginResponse
}

@RestController
internal class JoinOrLoginControllerImpl(
    val useCase: JoinOrLoginUseCase
) : JoinOrLoginController {
    override fun joinOrLogin(request: JoinOrLoginRequest): JoinOrLoginResponse =
        useCase.joinOrLogin(request.toMessage()).run { JoinOrLoginResponse.from(first, second) }
}
