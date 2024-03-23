package l1a.jjakkak.core.domain.address.repository

import l1a.jjakkak.core.domain.address.model.AdditionalAddressInfo
import l1a.jjakkak.core.domain.address.model.Coordinate
import l1a.jjakkak.core.domain.address.model.SearchedAddress
import l1a.jjakkak.infra.domain.address.model.AddressObject
import org.springframework.stereotype.Service

@Service
interface AddressRepository {
    //주소검색
    fun findAddressByKeyword(keyword: String): List<SearchedAddress>
    //좌표검ㅅ색
    fun findCoordinateByAddressInfo(addrObj: AdditionalAddressInfo): Coordinate
}