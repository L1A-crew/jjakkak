package com.zzekak.domain.user.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.UserQuery
import java.time.Instant

@JsonSerialize
internal data class GetUserResponse(
    val id: UserId,
    val name: String,
    val authentication: AuthenticationContent,
    val agreement: AgreementContent,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    companion object {
        fun from(src: UserQuery) =
            with(src) {
                GetUserResponse(
                    id = id,
                    name = name,
                    authentication = AuthenticationContent.from(authentication),
                    agreement = AgreementContent.from(agreement),
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
    }
}
