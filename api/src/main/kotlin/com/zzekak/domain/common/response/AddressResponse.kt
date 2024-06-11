package com.zzekak.domain.common.response

import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId

/**
 * @since 2024-05-25
 */
internal data class AddressResponse(
    val id: AppointmentAddressId,
    val cityOrProvince: String,
    val districtOrCity: String,
    val postalCode: String,
    val jibunAddress: String,
    val roadAddress: String,
    val x: String,
    val y: String
) {
    companion object {
        fun from(src: AppointmentAddress) =
            with(src) {
                AddressResponse(
                    id = id,
                    cityOrProvince = address.cityOrProvince,
                    districtOrCity = address.districtOrCity,
                    postalCode = address.postalCode,
                    jibunAddress = address.jibunAddress,
                    roadAddress = address.roadAddress,
                    x = address.x,
                    y = address.y,
                )
            }
    }
}
