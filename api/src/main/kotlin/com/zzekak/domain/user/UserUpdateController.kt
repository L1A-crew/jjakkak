package com.zzekak.domain.user

import com.zzekak.ApiUrl
import com.zzekak.domain.user.reqeust.UserUpdateRequest
import com.zzekak.domain.user.response.GetUserResponse
import com.zzekak.domain.user.usecase.UserUpdateUseCase
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
)
internal interface UserUpdateController {
    @PostMapping(ApiUrl.USER_UPDATE)
    fun update(
        @AuthenticationPrincipal userId: UUID,
        @RequestBody request: UserUpdateRequest
    ): GetUserResponse
}

@RestController
internal class UserUpdateControllerImpl(
    val userUpdateUseCase: UserUpdateUseCase
) : UserUpdateController {
    override fun update(
        userId: UUID,
        request: UserUpdateRequest
    ): GetUserResponse =
        userUpdateUseCase.update(
            message =
                UserUpdateUseCase.UserUpdateMessage(
                    userId = UserId(userId),
                    name = request.name,
                    marketingConsent = request.marketingConsent,
                    locationConsent = request.locationConsent,
                    pushNotificationConsent = request.pushNotificationConsent,
                ),
        ).run { GetUserResponse.from(this) }
}
