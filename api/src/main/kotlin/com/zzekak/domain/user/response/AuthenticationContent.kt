package com.zzekak.domain.user.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.domain.user.AuthenticationId
import com.zzekak.domain.user.AuthenticationQuery
import com.zzekak.domain.user.AuthenticationType
import java.time.Instant

@JsonSerialize
internal data class AuthenticationContent(
    val id: AuthenticationId,
    val type: AuthenticationType,
    val createdAt: Instant,
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
