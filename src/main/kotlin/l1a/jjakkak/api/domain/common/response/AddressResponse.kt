package l1a.jjakkak.api.domain.common.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.core.domain.address.model.AddressId
import l1a.jjakkak.core.domain.address.model.IdentifierAddress

data class AddressResponse(
    @JsonProperty("id")
    val id: AddressId,
    @JsonProperty("cityOrProvince")
    val cityOrProvince: String,
    @JsonProperty("districtOrCity")
    val districtOrCity: String,
    @JsonProperty("postalCode")
    val postalCode: String,
    @JsonProperty("jibunAddress")
    val jibunAddress: String,
    @JsonProperty("roadAddress")
    val roadAddress: String
) {
    companion object {
        fun from(src: IdentifierAddress) =
            with(src) {
                AddressResponse(
                    id = id,
                    cityOrProvince = cityOrProvince,
                    districtOrCity = districtOrCity,
                    postalCode = postalCode,
                    jibunAddress = jibunAddress,
                    roadAddress = roadAddress
                )
            }
    }
}
