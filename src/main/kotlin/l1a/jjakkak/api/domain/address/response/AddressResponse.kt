package l1a.jjakkak.api.domain.address.response

import l1a.jjakkak.core.domain.address.SearchedAddress

internal data class AddressResponse(val content: List<SearchedAddressContent>) {
    companion object {
        fun from(src: Collection<SearchedAddress>): AddressResponse =
            AddressResponse(
                content = src.map { SearchedAddressContent.from(it) }
            )
    }
}