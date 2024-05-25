package l1a.jjakkak.core.domain.address.model

interface SearchedPathResponse {
    val departureTime: String
    val arrivalTime: String
    val duration: String
    val startAddress: String
    val endAddress: String

    companion object {
        fun create(
            departureTime: String,
            arrivalTime: String,
            duration: String,
            startAddress: String,
            endAddress: String
        ): SearchedPathResponse =
            SearchedPathResponseImpl(
                departureTime = departureTime,
                arrivalTime = arrivalTime,
                duration = duration,
                startAddress = startAddress,
                endAddress = endAddress,
            )
    }
}

data class SearchedPathResponseImpl(
    override val departureTime: String,
    override val arrivalTime: String,
    override val duration: String,
    override val startAddress: String,
    override val endAddress: String
) : SearchedPathResponse
