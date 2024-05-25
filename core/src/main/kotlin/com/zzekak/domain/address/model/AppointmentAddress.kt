package com.zzekak.domain.address.model

import com.zzekak.core.domain.common.IdTypeUUID
import java.util.UUID

@JvmInline
value class AppointmentAddressId(override val value: UUID) : IdTypeUUID

interface AppointmentAddress {
    val id: AppointmentAddressId
    val address: Address

    companion object {
        fun create(
            id: AppointmentAddressId,
            cityOrProvince: String,
            districtOrCity: String,
            postalCode: String,
            roadAddress: String,
            x: String,
            y: String,
            buildingName: String,
            undergroundYn: String,
            jibunAddress: String
        ): AppointmentAddress =
            AppointmentAddressImpl(
                id = id,
                address =
                    Address.create(
                        address = roadAddress,
                        buildingName = buildingName,
                        undergroundYn = undergroundYn,
                        x = x,
                        y = y,
                        zoneNo = postalCode,
                        region1DepthName = cityOrProvince,
                        region2DepthName = districtOrCity,
                        jibunAddress = jibunAddress,
                    ),
            )
    }
}

internal data class AppointmentAddressImpl(
    override val id: AppointmentAddressId,
    override val address: Address
) : AppointmentAddress
