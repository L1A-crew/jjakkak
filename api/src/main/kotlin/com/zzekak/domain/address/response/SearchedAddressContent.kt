package com.zzekak.domain.address.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.zzekak.domain.address.model.SearchedAddress

internal data class SearchedAddressContent(
    @JsonProperty("roadAddress")
    val roadAddress: String,
    @JsonProperty("jibunAddress")
    val jibunAddress: String,
    @JsonProperty("buildingName")
    val buildingName: String,
    @JsonProperty("mountainYn")
    val mountainYn: String,
    @JsonProperty("undergroundYn")
    val undergroundYn: String,
    @JsonProperty("hCode")
    val hCode: String,
    @JsonProperty("x")
    val x: String,
    @JsonProperty("y")
    val y: String,
    @JsonProperty("cityOrProvince")
    val cityOrProvince: String,
    @JsonProperty("postalCode")
    val postalCode: String,
    @JsonProperty("districtOrCity")
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
