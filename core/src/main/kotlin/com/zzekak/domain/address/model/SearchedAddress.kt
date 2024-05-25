package com.zzekak.domain.address.model

interface SearchedAddress : Address, AdditionalAddressInfo {
    companion object {
        fun create(
            roadAddress: String,
            buildingName: String,
            jibunAddress: String,
            mountainYn: String,
            undergroundYn: String,
            hCode: String,
            x: String,
            y: String,
            postalCode: String,
            cityOrProvince: String,
            districtOrCity: String
        ): SearchedAddress =
            SearchedAddressImpl(
                roadAddress = roadAddress,
                buildingName = buildingName,
                jibunAddress = jibunAddress,
                mountainYn = mountainYn,
                undergroundYn = undergroundYn,
                hCode = hCode,
                x = x,
                y = y,
                postalCode = postalCode,
                cityOrProvince = cityOrProvince,
                districtOrCity = districtOrCity,
            )

        // 검색되는 값이 없는경우 빈 object로 내려줌.
        fun createEmptyObject(): SearchedAddress = SearchedAddressImpl("", "", "", "", "", "", "", "", "", "", "")
    }
}

internal data class SearchedAddressImpl(
    override val roadAddress: String,
    override val jibunAddress: String,
    override val buildingName: String,
    override val mountainYn: String,
    override val undergroundYn: String,
    override val hCode: String,
    override val x: String,
    override val y: String,
    override val postalCode: String,
    override val cityOrProvince: String,
    override val districtOrCity: String,
) : SearchedAddress
