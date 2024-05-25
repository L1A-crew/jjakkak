package com.zzekak.domain.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.core.domain.user.Agreement
import java.time.Instant

@JsonSerialize
internal data class AgreementContent(
    @JsonProperty("marketingConsent")
    val marketingConsent: Instant?,
    @JsonProperty("locationConsent")
    val locationConsent: Instant?,
    @JsonProperty("pushNotificationConsent")
    val pushNotificationConsent: Instant?
) {
    companion object {
        fun from(src: Agreement) =
            with(src) {
                AgreementContent(
                    marketingConsent = marketingConsent,
                    locationConsent = locationConsent,
                    pushNotificationConsent = pushNotificationConsent,
                )
            }
    }
}
