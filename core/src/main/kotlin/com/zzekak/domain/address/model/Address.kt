package com.zzekak.domain.address.model

// 주소 정보 기본 모델
interface Address {
    val roadAddress: String // 도로명주소 전체 ex) 서울시 강남구 테헤란로 501
    val buildingName: String // 건물명
    val undergroundYn: String // 지하 여부
    val x: String // x좌표
    val y: String // y좌표
    val postalCode: String // 우편번호
    val cityOrProvince: String // 시, 도
    val districtOrCity: String // 시, 군
    val jibunAddress: String // 지번주소

    companion object {
        fun create(
            address: String,
            buildingName: String,
            undergroundYn: String,
            x: String,
            y: String,
            zoneNo: String,
            region1DepthName: String,
            region2DepthName: String,
            jibunAddress: String
        ): Address =
            AddressImpl(
                roadAddress = address,
                buildingName = buildingName,
                undergroundYn = undergroundYn,
                x = x,
                y = y,
                postalCode = zoneNo,
                cityOrProvince = region1DepthName,
                districtOrCity = region2DepthName,
                jibunAddress = jibunAddress,
            )
    }
}

internal data class AddressImpl(
    override val roadAddress: String,
    override val buildingName: String,
    override val undergroundYn: String,
    override val x: String,
    override val y: String,
    override val postalCode: String,
    override val cityOrProvince: String,
    override val districtOrCity: String,
    override val jibunAddress: String
) : Address
