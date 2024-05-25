package com.zzekak.domain.address.repository

import com.zzekak.domain.address.model.SearchedAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class AddressRepositoryImpl(
    // 주소검색 apikey
    @Value("\${lia.auth.social-login-app-key}")
    val kakaaoAppKey: String,
) : AddressRepository {
    /**
     * 카카오 로컬 에서 제공하는 API 의 결과 값을 파싱, [Address] 에 담아서 반환한다.
     */
    override fun findAddressByKeyword(keyword: String): SearchedAddress =
        ConnectionModule("KakaoAK $kakaaoAppKey").searchAddr(keyword)
}
