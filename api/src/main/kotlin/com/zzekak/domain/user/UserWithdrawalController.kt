package com.zzekak.domain.user

import com.zzekak.ApiUrl
import com.zzekak.domain.user.reqeust.UserWithdrawalRequest
import com.zzekak.domain.user.response.WithdrawalResponse
import com.zzekak.domain.user.usecase.WithdrawalUseCase
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface UserWithdrawalController {
    @PutMapping(ApiUrl.USER_WITHDRAWAL)
    fun userWithdrawal(
        @RequestHeader headers: HttpHeaders
    ): WithdrawalResponse
}

@RestController
internal class UserWithdrawalControllerImpl(
    val useCase: WithdrawalUseCase
) : UserWithdrawalController {
    override fun userWithdrawal(headers: HttpHeaders): WithdrawalResponse {
        val token = headers["Authorization"].toString().replace("Bearer ", "")
        return useCase.withdrawal(UserWithdrawalRequest(token).toMessage()).run { WithdrawalResponse.from(this) }
    }
}
