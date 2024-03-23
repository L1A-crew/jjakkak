package l1a.jjakkak.core.domain.address.usecase

import l1a.jjakkak.core.domain.address.Address
import l1a.jjakkak.core.domain.address.SearchedAddress
import l1a.jjakkak.core.domain.address.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class FindAddressUseCase(
    private val addressRepository: AddressRepository
) {
    fun findAddressByKeyword(keyword: String): List<SearchedAddress> =
        addressRepository.findAddressByKeyword(keyword)
}