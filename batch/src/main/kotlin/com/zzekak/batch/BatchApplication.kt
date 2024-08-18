package com.zzekak.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan(basePackages = ["com.zzekak"])
@EnableJpaRepositories(
    basePackages = [
        "com.zzekak.domain",
        "com.zzekak.batch.push.repository",
    ],
)
@EntityScan(
    basePackages = [
        "com.zzekak.domain",
    ],
)
@SpringBootApplication
class BatchApplication

fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args)
}
