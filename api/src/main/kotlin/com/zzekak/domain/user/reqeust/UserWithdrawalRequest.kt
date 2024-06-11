package com.zzekak.domain.user.reqeust

import com.auth0.jwt.JWT
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.message.WithdrawalMessage
import com.zzekak.exception.ExceptionEnum
import com.zzekak.exception.ZzekakException
import java.util.UUID

@JsonDeserialize
data class UserWithdrawalRequest(
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
