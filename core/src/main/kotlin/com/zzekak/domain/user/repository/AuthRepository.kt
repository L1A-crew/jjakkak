package com.zzekak.core.domain.user.repository

interface AuthRepository {
    fun getKakaoLoginPublicKey(): List<RSAPublicKeyInfo>

    fun getAppleLoginPublicKey(): List<RSAPublicKeyInfo>

    data class RSAPublicKeyInfo(
        val kid: String,
        val kty: String,
        val alg: String,
        val use: String,
        val n: String,
        val e: String
    )
}
