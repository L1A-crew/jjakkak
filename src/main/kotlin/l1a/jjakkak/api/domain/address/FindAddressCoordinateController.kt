package l1a.jjakkak.api.domain.address

import l1a.jjakkak.api.ApiUrl
import l1a.jjakkak.api.domain.address.request.CoordinateSearchRequest
import l1a.jjakkak.core.domain.address.usecase.FindAddressCoordinateUseCase
import l1a.jjakkak.infra.domain.address.model.CoordinateResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
internal interface FindAddressCoordinateController {
    @GetMapping(ApiUrl.ADDRESS_COORDINATE_SEARCH)
    fun findAddressCoordinate(@ModelAttribute request: CoordinateSearchRequest): CoordinateResponse
}

@RestController
internal class FindAddressCoordinateControllerImpl(
    private val findAddressCoordinateUseCase: FindAddressCoordinateUseCase
) : FindAddressCoordinateController {
    override fun findAddressCoordinate(request: CoordinateSearchRequest): CoordinateResponse =
        findAddressCoordinateUseCase.findCoordinateByAddressInfo(request.toDomain())
            .run { CoordinateResponse.from(this) }
}
