package com.zzekak.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.core.domain.user.UserId
import com.zzekak.core.domain.user.UserQuery
import com.zzekak.domain.user.dto.AuthenticationTypeDto
import java.time.Instant

@JsonSerialize
internal data class UserCreateResponse(
    @JsonProperty("userId")
    val userId: UserId,
    @JsonProperty("authenticationType")
    val authenticationType: AuthenticationTypeDto,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
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
