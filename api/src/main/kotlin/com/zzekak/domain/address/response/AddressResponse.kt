package com.zzekak.domain.address.response

import com.zzekak.domain.address.model.SearchedAddress

internal data class AddressResponse(val content: SearchedAddress) {
    companion object {
        fun from(src: SearchedAddress): AddressResponse = AddressResponse(content = src)
    }
}
