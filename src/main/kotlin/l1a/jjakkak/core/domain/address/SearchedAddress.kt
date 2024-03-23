package l1a.jjakkak.core.domain.address

/**
 * 좌표 검색을 위한 모델
 */
interface SearchedAddress : Address {
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
}