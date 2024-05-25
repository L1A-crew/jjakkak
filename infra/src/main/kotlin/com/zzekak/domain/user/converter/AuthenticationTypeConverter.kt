package com.zzekak.infra.domain.user.converter

import com.zzekak.domain.user.AuthenticationType
import jakarta.persistence.AttributeConverter

class AuthenticationTypeConverter : AttributeConverter<AuthenticationType, String> {
    override fun convertToDatabaseColumn(attribute: AuthenticationType): String = attribute.code

    override fun convertToEntityAttribute(dbData: String): AuthenticationType = AuthenticationType.from(dbData)
}
