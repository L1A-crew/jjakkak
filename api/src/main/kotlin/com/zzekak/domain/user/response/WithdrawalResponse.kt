package com.zzekak.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.core.domain.user.WithdrawalResult

@JsonSerialize
internal class WithdrawalResponse(
    @JsonProperty("result")
    val quitResult: Char,
    @JsonProperty("message")
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
