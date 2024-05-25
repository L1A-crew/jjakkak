package com.zzekak.domain.address.repository

import com.zzekak.domain.address.model.SearchedAddress
import org.springframework.stereotype.Service

@Service
interface AddressRepository {
    // 주소검색
    fun findAddressByKeyword(keyword: String): SearchedAddress
}
