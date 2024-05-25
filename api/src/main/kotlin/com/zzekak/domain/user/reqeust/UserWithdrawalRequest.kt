package com.zzekak.domain.user.reqeust

import com.auth0.jwt.JWT
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.zzekak.core.domain.user.UserId
import com.zzekak.core.domain.user.message.WithdrawalMessage
import com.zzekak.exception.ExceptionEnum
import com.zzekak.exception.ZzekakException
import java.util.UUID

@JsonDeserialize
data class UserWithdrawalRequest(
    @JsonProperty("token")
    val token: String,
) {
    fun toMessage(): WithdrawalMessage =
        WithdrawalMessage(
            userId = UserId(decodeToken(token)),
        )

    private fun decodeToken(token: String): UUID =
        try {
            UUID.fromString(JWT.decode(token).subject)
        } catch (e: Exception) {
            throw ZzekakException(ExceptionEnum.ILLEGAL_TOKEN)
        }
}
