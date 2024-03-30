package l1a.jjakkak.infra.domain.address.repository

import kotlinx.serialization.json.*
import l1a.jjakkak.core.domain.address.Address
import l1a.jjakkak.core.domain.address.Coordinate
import l1a.jjakkak.core.domain.exception.ZzekakException
import l1a.jjakkak.infra.domain.address.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.UriBuilder

internal class ConnectionModule(val confmKey: String) {
    private val addrUrl = "https://business.juso.go.kr/addrlink/addrLinkApi.do"
    private val coodrUrl = "https://business.juso.go.kr/addrlink/addrCoordApi.do"
    //주소(addr)검색 / 좌표(coord)검색

    fun searchAddr(keyword: String): List<Address>{
        var addrList: MutableList<Address> = arrayListOf()
        val addrRslt = connectionJuso(keyword)
        addrRslt.results.let { it: Results? ->
            it?.juso!!.forEach { el ->
                val addr: AddressObject = AddressObject(el.siNm, el.sggNm, el.zipNo, el.jibunAddr, el.roadAddrPart1,
                    el.admCd, el.rnMgtSn, el.udrtYn, el.buldMnnm, el.buldSlno)
                addrList.add(addr)
            }

        }
        return addrList
    }
    fun searchCoordinate(aObj: AddressObject): CoordinateResponse {
        val srchRslt = connectionCoordinate(aObj)
        var result: CoordinateResponse = CoordinateResponse("0.0", "0.0")
        srchRslt.results.let { it: Results? ->
            it?.juso!!.forEach { el ->
                result = CoordinateResponse(el.entX, el.entY)
            }
        }
        return result
    }

    
    //도로명 주소 검색
    fun connectionJuso(keyword: String
    ): AddressResponse{
        val req: JsonObject = AddressRequest(keyword, confmKey).returnToJSON()
        val response: AddressResponse = connection(addrUrl, req)
        return response
    }

    //좌표 검색
    fun connectionCoordinate(aObj: AddressObject): AddressResponse{
        //admCd: String, rnMgtSn: String, udrtYn: String, buldMnnm: String, buldSlno: String
        var req: JsonObject = CoordinateRequest(confmKey, aObj.admCd, aObj.rnMgtSn, aObj.udrtYn,
                                                aObj.buldMnnm, aObj.buldSlno).returnToJSONString()

        val response: AddressResponse = connection(coodrUrl, req)
        return response
    }

    fun connection(url: String, req: JsonObject): AddressResponse {

        val factory = DefaultUriBuilderFactory(url).apply { this.encodingMode = DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT }
        val webClient = WebClient.builder()
            .uriBuilderFactory(factory)
            .build()

        val response = webClient
            .get()
            .uri{uriBuilder: UriBuilder ->
                var iter = req.keys.iterator()
                while(iter.hasNext()){
                    var key =iter.next()
                    uriBuilder.queryParam(key, req.get(key).toString().replace("\"", ""))
                }
                uriBuilder.build()
            }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(AddressResponse::class.java)
            .onErrorMap { e ->
                ZzekakException(e.message)
            }
            .block()
        return response!!
    }
}