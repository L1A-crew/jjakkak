package com.zzekak.domain.user.reqeust

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize
internal data class UserUpdateRequest(
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("marketingConsent")
    val marketingConsent: Boolean?,
    @JsonProperty("locationConsent")
    val locationConsent: Boolean?,
    @JsonProperty("pushNotificationConsent")
    val pushNotificationConsent: Boolean?
)
