package com.zzekak.domain.user

import java.time.Instant

interface Agreement {
    val marketingConsent: Instant?
    val locationConsent: Instant?
    val pushNotificationConsent: Instant?

    companion object {
        val EMPTY = create(null, null, null)

        fun create(
            marketingConsent: Instant?,
            locationConsent: Instant?,
            pushNotificationConsent: Instant?
        ): Agreement =
            AgreementImpl(
                marketingConsent = marketingConsent,
                locationConsent = locationConsent,
                pushNotificationConsent = pushNotificationConsent,
            )
    }
}

internal data class AgreementImpl(
    override val marketingConsent: Instant?,
    override val locationConsent: Instant?,
    override val pushNotificationConsent: Instant?
) : Agreement
