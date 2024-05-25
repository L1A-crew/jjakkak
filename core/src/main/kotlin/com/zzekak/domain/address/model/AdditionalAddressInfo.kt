package com.zzekak.domain.address.model

/**
 * 좌표 검색을 위한 모델
 */
interface AdditionalAddressInfo {
    val jibunAddress: String // 지번주소
    val mountainYn: String // 산 여부
    val hCode: String // 행정동코드

    companion object {
        fun create(
            jibunAddress: String,
            mountainYn: String,
            hCode: String,
        ): AdditionalAddressInfo =
            AdditionalAddressInfoImpl(
                jibunAddress = jibunAddress,
                mountainYn = mountainYn,
                hCode = hCode,
            )
    }
}

internal data class AdditionalAddressInfoImpl(
    override val jibunAddress: String,
    override val mountainYn: String,
    override val hCode: String
) : AdditionalAddressInfo
