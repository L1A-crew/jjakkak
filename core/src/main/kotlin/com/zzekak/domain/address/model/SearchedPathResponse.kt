package com.zzekak.domain.address.model

data class SearchedPathResponse(
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val startAddress: String,
    val endAddress: String,
    val distance: String
)
