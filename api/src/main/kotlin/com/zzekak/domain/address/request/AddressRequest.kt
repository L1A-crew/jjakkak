package com.zzekak.domain.address.request

internal data class AddressRequest(
    val cityOrProvince: String,
    val districtOrCity: String,
    val postalCode: String,
    val jibunAddress: String,
    val roadAddress: String,
    val x: String,
    val y: String,
    val undergroundYn: String,
    val buildingName: String
)
