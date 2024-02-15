package com.example.eyeserver.config

import com.example.eyeserver.Security.JwtAuthenticationFilter
import com.example.eyeserver.Security.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer

import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtTokenProvider: JwtTokenProvider
){
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf{
                    csrfConfig: CsrfConfigurer<HttpSecurity> -> csrfConfig.disable()
            }
            .headers { headerConfig: HeadersConfigurer<HttpSecurity?> ->
                headerConfig.frameOptions { frameOptionsConfig -> frameOptionsConfig.disable() }
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/agency/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/user/**")).hasRole("Manager")
                    .requestMatchers(AntPathRequestMatcher("/unity/**")).hasRole("User")
                    .anyRequest().authenticated()
            }
            .formLogin { formLogin -> formLogin.disable() }
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }


}
