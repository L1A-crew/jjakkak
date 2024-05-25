package com.zzekak.core.domain.address.model

interface Coordinate {
    val x: String
    val y: String

    companion object {
        fun create(
            x: String,
            y: String
        ): Coordinate =
            CoordinateImpl(
                x = x,
                y = y,
            )
    }
}

internal data class CoordinateImpl(
    override val x: String,
    override val y: String
) : Coordinate
