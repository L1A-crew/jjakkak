package com.zzekak.domain.address.model

import com.fasterxml.jackson.annotation.JsonProperty

internal data class AddressResponse(
    val documents: List<Documents>?
)

internal data class Documents(
    // 지번주소
    @JsonProperty("address")
    val addressContent: AddressContent,
    @JsonProperty("address_name")
    val addressName: String,
    @JsonProperty("address_type")
    val addressType: String,
    // 도로명주소
    @JsonProperty("road_address")
    val roadAddress: RoadAddress,
    val x: String,
    val y: String
)

// 지번주소 object
internal data class AddressContent(
    @JsonProperty("address_name")
    val addressName: String,
    @JsonProperty("b_code")
    val bCode: String,
    @JsonProperty("h_code")
    val hCode: String,
    @JsonProperty("main_address_no")
    val mainAddressNo: String,
    @JsonProperty("mountain_yn")
    val mountainYn: String,
    @JsonProperty("region_1depth_name")
    val region1DepthName: String,
    @JsonProperty("region_2depth_name")
    val region2DepthName: String,
    @JsonProperty("region_3depth_h_name")
    val region3DepthHName: String,
    @JsonProperty("region_3depth_name")
    val region3DepthName: String,
    @JsonProperty("sub_address_no")
    val subAddressNo: String,
    val x: String,
    val y: String
)

// 도로명주소 object
internal data class RoadAddress(
    @JsonProperty("address_name")
    val addressName: String,
    @JsonProperty("building_name")
    val buildingName: String,
    @JsonProperty("main_building_no")
    val mainBuildingNo: String,
    @JsonProperty("region_1depth_name")
    val region1DepthName: String,
    @JsonProperty("region_2depth_name")
    val region2DepthName: String,
    @JsonProperty("region_3depth_name")
    val region3DepthName: String,
    @JsonProperty("road_name")
    val roadName: String,
    @JsonProperty("sub_building_no")
    val subBuildingNo: String,
    @JsonProperty("underground_yn")
    val undergroundYn: String,
    val x: String,
    val y: String,
    @JsonProperty("zone_no")
    val zoneNo: String
)

data class AddressObject(
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
    override val districtOrCity: String
) : SearchedAddress
