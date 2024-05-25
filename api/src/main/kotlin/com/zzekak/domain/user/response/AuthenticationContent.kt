package com.zzekak.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.core.domain.user.AuthenticationId
import com.zzekak.core.domain.user.AuthenticationQuery
import com.zzekak.domain.user.AuthenticationType
import java.time.Instant

@JsonSerialize
internal data class AuthenticationContent(
    @JsonProperty("id")
    val id: AuthenticationId,
    @JsonProperty("type")
    val type: AuthenticationType,
    @JsonProperty("createdAt")
    val createdAt: Instant,
    @JsonProperty("updatedAt")
    val updatedAt: Instant
) {
    companion object {
        fun from(src: AuthenticationQuery) =
            with(src) {
                AuthenticationContent(
                    id = id,
                    type = type,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
    }
}
