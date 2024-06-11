package com.zzekak.domain.address.response

import com.zzekak.domain.address.model.Coordinate

internal data class CoordinateResponse(
    override val x: String,
    override val y: String
) : Coordinate {
    companion object {
        fun from(src: Coordinate): CoordinateResponse = CoordinateResponse(x = src.x, y = src.y)
    }
}
