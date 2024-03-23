package l1a.jjakkak

import l1a.jjakkak.infra.domain.address.model.AddressObject
import l1a.jjakkak.infra.domain.address.repository.ConnectionModule
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AddressTest (
) {

    @Test
    fun requestTest() {
        val keyword: String = "상암동 월드컵파크"
        var result = ConnectionModule("").searchAddr(keyword) as List<AddressObject>
        var coordResult = ConnectionModule("").searchCoordinate(result[0])
        println("coord Rslt => ${coordResult}")
    }
}