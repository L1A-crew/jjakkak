package com.zzekak.domain.address.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
internal data class AddressRequest(
    val query: String,
    val analyze_type: String,
    val page: Int,
    val size: Int
) {
    constructor(keyword: String) : this(
        query = keyword,
        // 정확히 입력한 주소에 대해서만 탐색.비슷한 주소는 탐색하지 않음.
        analyze_type = "exact",
        page = 1,
        size = 30,
    )

    fun returnToJSON(): JsonObject {
        return Json.encodeToJsonElement(this).jsonObject
    }
}
