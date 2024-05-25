package com.zzekak.infra.domain.user.entity

import com.zzekak.core.domain.user.Agreement
import com.zzekak.core.domain.user.UserCommand
import com.zzekak.core.domain.user.UserId
import com.zzekak.infra.domain.common.entity.AuditableBase
import com.zzekak.infra.domain.user.entity.UserEntity.Companion.TABLE_USER
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_USER)
class UserEntity(
    @Id
    @Column(name = COLUMN_USER_ID)
    val userId: UUID,
    @Column(name = COLUMN_NAME)
    var name: String,
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userEntity", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = AuthenticationEntity.COLUMN_USER_ID)
    val authenticationEntity: AuthenticationEntity,
    @Column(name = COLUMN_MARKETING_CONSENT)
    var marketingConsent: Instant?,
    @Column(name = COLUMN_LOCATION_CONSENT)
    var locationConsent: Instant?,
    @Column(name = COLUMN_PUSH_NOTIFICATION_CONSENT)
    var pushNotificationConsent: Instant?,
    @Column(name = COLUMN_IS_REMOVED)
    var isRemoved: Boolean
) : AuditableBase() {
    fun toDomain(): UserCommand =
        UserCommand.create(
            id = UserId(userId),
            name = name,
            authentication = authenticationEntity.toDomain(),
            agreement =
                Agreement.create(
                    marketingConsent = marketingConsent,
                    locationConsent = locationConsent,
                    pushNotificationConsent = pushNotificationConsent,
                ),
            isRemoved = isRemoved,
        )

    companion object {
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_MARKETING_CONSENT = "marketing_consent"
        private const val COLUMN_LOCATION_CONSENT = "location_consent"
        private const val COLUMN_PUSH_NOTIFICATION_CONSENT = "push_notification_consent"
        private const val COLUMN_IS_REMOVED = "is_removed"

        fun from(src: UserCommand): UserEntity =
            UserEntity(
                userId = src.id.value,
                name = src.name,
                authenticationEntity = AuthenticationEntity.from(src.authenticationCommand),
                marketingConsent = src.agreement.marketingConsent,
                locationConsent = src.agreement.locationConsent,
                pushNotificationConsent = src.agreement.pushNotificationConsent,
                isRemoved = src.isRemoved,
            ).apply {
                authenticationEntity.userEntity = this
            }
    }
}
