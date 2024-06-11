package com.zzekak.domain.user.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.domain.user.WithdrawalResult

@JsonSerialize
internal class WithdrawalResponse(
    val quitResult: Char,
    val message: String
) {
    companion object {
        fun from(result: WithdrawalResult) =
            WithdrawalResponse(
                quitResult = result.result,
                message = result.message,
            )
    }
}
