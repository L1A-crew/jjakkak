package l1a.jjakkak.core.domain.address.model

interface PathFindResponse {
    val geocoderStatus: String

    companion object {
        fun create(geocoderStatus: String): PathFindResponse =
            PathFindResponseImpl(
                geocoderStatus = geocoderStatus,
            )
    }
}

internal data class PathFindResponseImpl(
    override val geocoderStatus: String
) : PathFindResponse
