package com.example.eyeserver.Security


import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.filter.GenericFilterBean

@CrossOrigin(origins = ["http://localhost:3000"])
@Component
class JwtAuthenticationFilter (
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val jwtToken: String? = jwtTokenProvider
            .resolveToken(request as HttpServletRequest)

        if ((jwtToken != null) &&
            jwtTokenProvider.validateToken(jwtToken)) {

            val authentication = jwtTokenProvider
                .userAuthentication(jwtToken)
            SecurityContextHolder
                .getContext()
                .authentication = authentication
        }
        chain.doFilter(request, response)
    }
}