package l1a.jjakkak.api.security

import com.auth0.jwt.exceptions.TokenExpiredException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import l1a.jjakkak.api.security.SecurityConfig.Companion.AUTHENTICATION_BY_PASS_LIST
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Instant

internal class JWTAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (AUTHENTICATION_BY_PASS_LIST.any { request.requestURI.contains(it) }) {
            filterChain.doFilter(request, response)
            return
        }

        runCatching {
            request.getHeader(HEADER_AUTHENTICATION)?.let { token ->
                if (jwtTokenProvider.validationToken(token)) {
                    jwtTokenProvider.getAuthentication(token)
                } else {
                    throw TokenExpiredException("token is expired", Instant.now())
                }
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

    companion object {
        const val HEADER_AUTHENTICATION = "Authorization"
    }
}
