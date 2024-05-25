package com.zzekak.domain.common.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.zzekak.domain.address.model.AppointmentAddress
import com.zzekak.domain.address.model.AppointmentAddressId

/**
 * @since 2024-05-25
 */
internal data class AddressResponse(
    @JsonProperty("id")
    val id: AppointmentAddressId,
    @JsonProperty("cityOrProvince")
    val cityOrProvince: String,
    @JsonProperty("districtOrCity")
    val districtOrCity: String,
    @JsonProperty("postalCode")
    val postalCode: String,
    @JsonProperty("jibunAddress")
    val jibunAddress: String,
    @JsonProperty("roadAddress")
    val roadAddress: String,
    @JsonProperty("x")
    val x: String,
    @JsonProperty("y")
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
