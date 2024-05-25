package l1a.com.zzekak.domain.address.request

import com.fasterxml.jackson.annotation.JsonProperty

internal data class AddressRequest(
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
    val x: String,
    val y: String,
    @JsonProperty("undergroundYn")
    val undergroundYn: String,
    @JsonProperty("buildingName")
    val buildingName: String
)
