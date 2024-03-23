package l1a.jjakkak.core.domain.address.model

/**
 * 좌표 검색을 위한 모델
 */
interface AdditionalAddressInfo {
    /** 행정 구역 코드 */
    val administrativeCode: String

    /** 도로명 코드 */
    val roadNameCode: String

    /** 지하 여부 */
    val undergroundIndicator: String

    /** 건물 본번 */
    val buildingMainNumber: String

    /** 건물 부번 */
    val buildingSubNumber: String

    companion object {
        fun create(
            administrativeCode: String,
            roadNameCode: String,
            undergroundIndicator: String,
            buildingMainNumber: String,
            buildingSubNumber: String
        ): AdditionalAddressInfo = AdditionalAddressInfoImpl(
            administrativeCode = administrativeCode,
            roadNameCode = roadNameCode,
            undergroundIndicator = undergroundIndicator,
            buildingMainNumber = buildingMainNumber,
            buildingSubNumber = buildingSubNumber
        )
    }
}

internal data class AdditionalAddressInfoImpl(
    override val administrativeCode: String,
    override val roadNameCode: String,
    override val undergroundIndicator: String,
    override val buildingMainNumber: String,
    override val buildingSubNumber: String
) : AdditionalAddressInfo
