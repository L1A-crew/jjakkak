package l1a.jjakkak.core.domain.address.usecase

import l1a.jjakkak.core.domain.address.model.AdditionalAddressInfo
import l1a.jjakkak.core.domain.address.model.Coordinate
import l1a.jjakkak.core.domain.address.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
internal class FindAddressCoordinateUseCase(
    val addressRepository: AddressRepository
) {
    fun findCoordinateByAddressInfo(additionalAddressInfo: AdditionalAddressInfo): Coordinate =
        addressRepository.findCoordinateByAddressInfo(additionalAddressInfo)
}