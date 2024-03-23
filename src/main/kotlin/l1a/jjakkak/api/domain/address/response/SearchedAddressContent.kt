package l1a.jjakkak.api.domain.address.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.core.domain.address.model.SearchedAddress

internal data class SearchedAddressContent(
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
    @JsonProperty("administrativeCode")
    val administrativeCode: String,
    @JsonProperty("roadNameCode")
    val roadNameCode: String,
    @JsonProperty("undergroundIndicator")
    val undergroundIndicator: String,
    @JsonProperty("buildingMainNumber")
    val buildingMainNumber: String,
    @JsonProperty("buildingSubNumber")
    val buildingSubNumber: String
) {
    companion object {
        fun from(src: SearchedAddress): SearchedAddressContent =
            with(src) {
                SearchedAddressContent(
                    cityOrProvince = cityOrProvince,
                    districtOrCity = districtOrCity,
                    postalCode = postalCode,
                    jibunAddress = jibunAddress,
                    roadAddress = roadAddress,
                    administrativeCode = administrativeCode,
                    roadNameCode = roadNameCode,
                    undergroundIndicator = undergroundIndicator,
                    buildingMainNumber = buildingMainNumber,
                    buildingSubNumber = buildingSubNumber
                )
            }
    }
}
