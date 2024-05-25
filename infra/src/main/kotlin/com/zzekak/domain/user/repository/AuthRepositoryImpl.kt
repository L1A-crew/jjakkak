package com.zzekak.infra.domain.user.repository

import com.fasterxml.jackson.annotation.JsonProperty
import com.zzekak.core.domain.user.repository.AuthRepository
import com.zzekak.util.ObjectMapper
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Repository
internal class AuthRepositoryImpl(
    @Value("\${lia.auth.social-login-kakao-public-key-url}")
    private val kakaoPublicKeyUrl: String,
    @Value("\${lia.auth.social-login-apple-public-key-url}")
    private val applePublicKeyUrl: String
) : AuthRepository, InitializingBean {
    lateinit var httpClient: HttpClient

    override fun getKakaoLoginPublicKey(): List<AuthRepository.RSAPublicKeyInfo> {
        val request =
            HttpRequest.newBuilder()
                .uri(URI(kakaoPublicKeyUrl))
                .GET()
                .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        val parsed =
            ObjectMapper.objectMapper.readValue(
                response.body(),
                KakaoPublicKeys::class.java,
            )

        return parsed.keys.map {
            AuthRepository.RSAPublicKeyInfo(
                kid = it.kid,
                kty = it.kty,
                alg = it.alg,
                use = it.use,
                n = it.n,
                e = it.e,
            )
        }
    }

    override fun getAppleLoginPublicKey(): List<AuthRepository.RSAPublicKeyInfo> {
        val request =
            HttpRequest.newBuilder()
                .uri(URI(applePublicKeyUrl))
                .GET()
                .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        val parsed =
            ObjectMapper.objectMapper.readValue(
                response.body(),
                ApplePublicKeys::class.java,
            )

        return parsed.keys.map {
            AuthRepository.RSAPublicKeyInfo(
                kid = it.kid,
                kty = it.kty,
                alg = it.alg,
                use = it.use,
                n = it.n,
                e = it.e,
            )
        }
    }

    private data class KakaoPublicKeys(
        @JsonProperty("keys")
        val keys: List<KeyInfo>
    ) {
        data class KeyInfo(
            @JsonProperty("kid")
            val kid: String,
            @JsonProperty("kty")
            val kty: String,
            @JsonProperty("alg")
            val alg: String,
            @JsonProperty("use")
            val use: String,
            @JsonProperty("n")
            val n: String,
            @JsonProperty("e")
            val e: String
        )
    }

    private data class ApplePublicKeys(
        @JsonProperty("keys")
        val keys: List<KeyInfo>
    ) {
        data class KeyInfo(
            @JsonProperty("kid")
            val kid: String,
            @JsonProperty("kty")
            val kty: String,
            @JsonProperty("alg")
            val alg: String,
            @JsonProperty("use")
            val use: String,
            @JsonProperty("n")
            val n: String,
            @JsonProperty("e")
            val e: String
        )
    }

    override fun afterPropertiesSet() {
        httpClient = HttpClient.newHttpClient()
    }
}
