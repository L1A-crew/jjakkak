package l1a.jjakkak.core.domain.address.model

interface SearchedAddress: Address, AdditionalAddressInfo {
    companion object {
        fun create(
            administrativeCode: String,
            roadNameCode: String,
            undergroundIndicator: String,
            buildingMainNumber: String,
            buildingSubNumber: String,
            cityOrProvince: String,
            districtOrCity: String,
            postalCode: String,
            jibunAddress: String,
            roadAddress: String
        ): SearchedAddress = SearchedAddressImpl(
            administrativeCode = administrativeCode,
            roadNameCode = roadNameCode,
            undergroundIndicator = undergroundIndicator,
            buildingMainNumber = buildingMainNumber,
            buildingSubNumber = buildingSubNumber,
            cityOrProvince = cityOrProvince,
            districtOrCity = districtOrCity,
            postalCode = postalCode,
            jibunAddress = jibunAddress,
            roadAddress = roadAddress
        )
    }
}

internal data class SearchedAddressImpl(
    override val administrativeCode: String,
    override val roadNameCode: String,
    override val undergroundIndicator: String,
    override val buildingMainNumber: String,
    override val buildingSubNumber: String,
    override val cityOrProvince: String,
    override val districtOrCity: String,
    override val postalCode: String,
    override val jibunAddress: String,
    override val roadAddress: String
) : SearchedAddress