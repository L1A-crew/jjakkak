package l1a.jjakkak.api.config.healthcheck

import l1a.jjakkak.api.ApiUrl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


private interface HealthCheckController {

    @GetMapping(ApiUrl.HEALTH_CHECK)
    fun healthCheck(): String
}
@RestController
class HealthCheckControllerImpl : HealthCheckController{
    override fun healthCheck(): String = "Started Zzekak Service"

}