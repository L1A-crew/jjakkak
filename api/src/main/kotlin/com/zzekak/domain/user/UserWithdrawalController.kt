package com.zzekak.domain.user

import com.zzekak.ApiUrl
import com.zzekak.domain.user.response.WithdrawalResponse
import com.zzekak.domain.user.usecase.WithdrawalUseCase
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface UserWithdrawalController {
    @PutMapping(ApiUrl.USER_WITHDRAWAL)
    fun userWithdrawal(
        @AuthenticationPrincipal userId: UUID,
    ): WithdrawalResponse
}

@RestController
internal class UserWithdrawalControllerImpl(
    val useCase: WithdrawalUseCase
) : UserWithdrawalController {
    override fun userWithdrawal(userId: UUID): WithdrawalResponse {
        return useCase.withdrawal(userId).run { WithdrawalResponse.from(this) }
    }
}
