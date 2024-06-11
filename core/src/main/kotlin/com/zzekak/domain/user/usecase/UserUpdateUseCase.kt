package com.zzekak.domain.user.usecase

import com.zzekak.domain.user.Agreement
import com.zzekak.domain.user.UserCommand
import com.zzekak.domain.user.UserId
import com.zzekak.domain.user.UserQuery
import com.zzekak.domain.user.repository.UserCommandRepository
import com.zzekak.domain.user.repository.UserQueryRepository
import org.springframework.stereotype.Service
import java.time.Instant

interface UserUpdateUseCase {
    fun update(message: UserUpdateMessage): UserQuery

    data class UserUpdateMessage(
        val userId: UserId,
        val name: String? = null,
        val marketingConsent: Boolean? = null,
        val locationConsent: Boolean? = null,
        val pushNotificationConsent: Boolean? = null
    )
}

@Service
internal data class UserUpdateUseCaseImpl(
    val userCommandRepo: UserCommandRepository,
    val userQueryRepo: UserQueryRepository
) : UserUpdateUseCase {
    override fun update(message: UserUpdateUseCase.UserUpdateMessage): UserQuery {
        val found = userCommandRepo.getById(message.userId)
        val now = Instant.now()

        UserCommand.create(
            id = found.id,
            name = message.name ?: found.name,
            authentication = found.authenticationCommand,
            agreement =
                Agreement.create(
                    marketingConsent =
                        getConsentInstant(
                            consent = message.marketingConsent,
                            exist = found.agreement.marketingConsent,
                            now = now,
                        ),
                    locationConsent =
                        getConsentInstant(
                            consent = message.locationConsent,
                            exist = found.agreement.locationConsent,
                            now = now,
                        ),
                    pushNotificationConsent =
                        getConsentInstant(
                            consent = message.pushNotificationConsent,
                            exist = found.agreement.pushNotificationConsent,
                            now = now,
                        ),
                ),
            isRemoved = false,
        ).run { userCommandRepo.save(this) }

        return userQueryRepo.getById(message.userId)
    }

    private fun getConsentInstant(
        consent: Boolean?,
        exist: Instant?,
        now: Instant
    ): Instant? =
        when (consent) {
            true -> now
            false -> null
            null -> exist
        }
}
