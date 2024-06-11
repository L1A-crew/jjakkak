package com.zzekak.domain.user.response

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.zzekak.domain.user.Agreement
import java.time.Instant

@JsonSerialize
internal data class AgreementContent(
    val marketingConsent: Instant?,
    val locationConsent: Instant?,
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
