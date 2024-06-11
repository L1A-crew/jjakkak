package com.zzekak.domain.user.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.UserQuery
import com.zzekak.domain.user.dto.AuthenticationTypeDto
import java.time.Instant

@JsonSerialize
internal data class UserCreateResponse(
    val userId: UserId,
    val authenticationType: AuthenticationTypeDto,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object {
        fun from(src: UserQuery) =
            UserCreateResponse(
                userId = src.id,
                authenticationType = AuthenticationTypeDto.from(src.authentication.type),
                createdAt = src.createdAt,
                updatedAt = src.updatedAt,
            )
    }
}
