package com.zzekak.domain.address

import com.zzekak.domain.address.repository.AddressRepositoryImpl
import com.zzekak.domain.address.repository.PathFindingRepositoryImpl
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class AddressTest {

    val addrRepository = AddressRepositoryImpl("54dbafb05b7dc5acde1a0c60d03ee9f2")
    val findPathRepository = PathFindingRepositoryImpl("AIzaSyDO8fvmA_C6tXBgKDyUJ1M04ptlG19oFjk")

    @Test
    fun searchAddrInfo () {
        // given
        val addr = "테헤란로 501"
        val addr2 = "강서로 12길 8-11"

        // when
        val result = addrRepository.findAddressByKeyword(addr)
        val result2 = addrRepository.findAddressByKeyword(addr2)
        println(result)
        println(result2)

    }

    @Test
    fun searchPathAndTime(){
        /**
         * x=126.84788713801, y=37.5311052079473
         * x=127.056821251348, y=37.5073809450356
         *
         * */
        val appointmentTime = ZonedDateTime.of(2024,7,28,15,0,0,0, ZoneId.of("Asia/Seoul"))
        val result = findPathRepository.findPath("126.84788713801",
            "37.5311052079473",
            "127.056821251348",
            "37.5073809450356",
            appointmentTime
            )
    }
}
