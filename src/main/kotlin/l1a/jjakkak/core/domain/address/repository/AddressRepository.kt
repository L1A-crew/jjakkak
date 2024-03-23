package l1a.jjakkak.core.domain.address.repository

import l1a.jjakkak.core.domain.address.Address
import l1a.jjakkak.core.domain.address.Coordinate
import l1a.jjakkak.core.domain.address.SearchedAddress
import l1a.jjakkak.infra.domain.address.model.AddressObject
import org.springframework.stereotype.Service

@Service
interface AddressRepository {
    //주소검색
    fun findAddressByKeyword(keyword: String): List<SearchedAddress>
    //좌표검ㅅ색
    fun findCoordinateByAddressInfo(addrObj: AddressObject): Coordinate
}