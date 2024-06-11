package com.zzekak.infra.domain.user.entity

import com.zzekak.domain.user.AuthenticationCommand
import com.zzekak.domain.user.AuthenticationId
import com.zzekak.domain.user.AuthenticationType
import com.zzekak.infra.domain.common.entity.AuditableBase
import com.zzekak.infra.domain.user.converter.AuthenticationTypeConverter
import com.zzekak.infra.domain.user.entity.AuthenticationEntity.Companion.TABLE_AUTHENTICATION
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = TABLE_AUTHENTICATION)
class AuthenticationEntity(
    @Id
    @Column(name = COLUMN_AUTHENTICATION_ID)
    val authenticationId: String,
    @Column(name = COLUMN_TYPE)
    @Convert(converter = AuthenticationTypeConverter::class)
    var type: AuthenticationType,
) : AuditableBase() {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = COLUMN_USER_ID)
    var userEntity: UserEntity? = null

    fun toDomain(): AuthenticationCommand =
        AuthenticationCommand.create(
            id = AuthenticationId(authenticationId),
            type = type,
        )

    companion object {
        const val TABLE_AUTHENTICATION = "authentication"
        private const val COLUMN_AUTHENTICATION_ID = "authentication_id"
        const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_TYPE = "type"

        fun from(src: AuthenticationCommand): AuthenticationEntity =
            AuthenticationEntity(
                authenticationId = src.id.value,
                type = src.type,
            )
    }
}
