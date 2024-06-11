package com.zzekak.domain.user.reqeust

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
internal data class UserUpdateRequest(
    val name: String?,
    val marketingConsent: Boolean?,
    val locationConsent: Boolean?,
    val pushNotificationConsent: Boolean?
)
