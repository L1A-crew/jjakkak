package com.zzekak

import com.zzekak.batch.push.config.SchedulerConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

/**
 * @since 2024-05-11
 */
@SpringBootApplication
@Import(SchedulerConfig::class)
class ZzekakApplication

fun main(args: Array<String>) {
    runApplication<ZzekakApplication>(*args)
}
