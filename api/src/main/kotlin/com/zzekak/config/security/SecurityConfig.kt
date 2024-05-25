package com.zzekak.config.security

import com.zzekak.ApiUrl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
internal class SecurityConfig(
    val jwtTokenProvider: JwtTokenProvider
) {
    @Bean
    fun jwtAuthenticationFilter(jwtTokenProvider: JwtTokenProvider): JWTAuthenticationFilter {
        return JWTAuthenticationFilter(jwtTokenProvider)
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers(*AUTHENTICATION_BY_PASS_LIST)
        }
    }

    @Bean
    fun securityFilterChain(
        httpSecurity: HttpSecurity,
        jwtAuthenticationFilter: JWTAuthenticationFilter
    ): SecurityFilterChain =
        httpSecurity
//            .sessionManagement {
//                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            }
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    // 로그인 및 등록 URL에 대한 요청은 인증 없이 허용
                    .requestMatchers(*AUTHENTICATION_BY_PASS_LIST).permitAll()
                    .anyRequest().authenticated()
            }
            // JWTAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 추가
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    companion object {
        val AUTHENTICATION_BY_PASS_LIST =
            listOf(
                ApiUrl.USER_JOIN_OR_LOGIN,
                ApiUrl.USER_TOKEN_REFRESH,
                "${ApiUrl.DOCS}/**",
                ApiUrl.HEALTH_CHECK,
                ApiUrl.EXCEPTION,
            ).toTypedArray()
    }
}
