package com.zzekak.config.healthcheck

import com.zzekak.ApiUrl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

interface HealthCheckController {
    @GetMapping(ApiUrl.HEALTH_CHECK)
    fun healthCheck(): String
}

@RestController
internal class HealthCheckControllerImpl : HealthCheckController {
    override fun healthCheck(): String = "Started Zzekak Service"
}
