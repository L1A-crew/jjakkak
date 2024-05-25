package com.zzekak.config.security

import com.zzekak.config.security.SecurityConfig.Companion.AUTHENTICATION_BY_PASS_LIST
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

internal class JWTAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : OncePerRequestFilter() {
    private val antPathMatcher = AntPathMatcher()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (shouldBypassAuthentication(request)) {
            filterChain.doFilter(request, response)
            return
        }

        runCatching {
            request.getHeader(HEADER_AUTHENTICATION)?.let { token ->
                jwtTokenProvider.validate(token)

                jwtTokenProvider.getAuthentication(token)
            } ?: throw InsufficientAuthenticationException("token is required to access service")
        }.onSuccess { authentication ->
            SecurityContextHolder.getContext().authentication = authentication
        }.onFailure { e ->
            SecurityContextHolder.clearContext()
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun shouldBypassAuthentication(request: HttpServletRequest): Boolean {
        return AUTHENTICATION_BY_PASS_LIST.any { pattern ->
            antPathMatcher.match(pattern, request.requestURI)
        }
    }

    companion object {
        const val HEADER_AUTHENTICATION = "Authorization"
    }
}
