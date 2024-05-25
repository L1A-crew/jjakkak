package l1a.com.zzekak.domain.address.response

import com.zzekak.core.domain.address.model.Coordinate

internal data class CoordinateResponse(
    override val x: String,
    override val y: String
) : Coordinate {
    companion object {
        fun from(src: Coordinate): CoordinateResponse = CoordinateResponse(x = src.x, y = src.y)
    }
}
