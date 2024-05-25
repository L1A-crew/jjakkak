package com.zzekak.domain.address.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
internal data class PathFindRequest(
    val origin: String,
    val destination: String,
    val mode: String,
    val transitMode: String,
    val key: String,
    val arrival_time: String
) {
    constructor(origin: String, destination: String, key: String, arrivalTime: String) : this(
        origin = origin,
        destination = destination,
        mode = "transit",
        key = key,
        transitMode = "",
        arrival_time = arrivalTime,
    )

    fun returnToJSON(): JsonObject {
        return Json.encodeToJsonElement(this).jsonObject
    }
}
