package com.zzekak.domain.address.response

import com.zzekak.domain.address.model.SearchedAddress

internal data class SearchedAddressContent(
    val roadAddress: String,
    val jibunAddress: String,
    val buildingName: String,
    val mountainYn: String,
    val undergroundYn: String,
    val hCode: String,
    val x: String,
    val y: String,
    val cityOrProvince: String,
    val postalCode: String,
    val districtOrCity: String
) {
    companion object {
        fun from(src: SearchedAddress): SearchedAddressContent =
            with(src) {
                SearchedAddressContent(
                    roadAddress = roadAddress,
                    jibunAddress = jibunAddress,
                    buildingName = buildingName,
                    mountainYn = mountainYn,
                    undergroundYn = undergroundYn,
                    hCode = hCode,
                    x = x,
                    y = y,
                    cityOrProvince = cityOrProvince,
                    postalCode = postalCode,
                    districtOrCity = districtOrCity,
                )
            }
    }
}
