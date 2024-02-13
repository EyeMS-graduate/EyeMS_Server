package com.example.eyeserver.Security

import com.example.eyeserver.AgencyLogin.Role.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class SecurityConfig (
    jwtAuthenticationFilter: JwtAuthenticationFilter
){
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf{
                    csrfConfig: CsrfConfigurer<HttpSecurity> -> csrfConfig.disable()
            }
            .headers { headerConfig: HeadersConfigurer<HttpSecurity?> ->
                headerConfig.frameOptions(
                    { frameOptionsConfig -> frameOptionsConfig.disable() }
                )
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/signUp")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/signIn")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/error")).permitAll()
                    .anyRequest().authenticated()
            }


            //.formLogin{login -> login.loginPage("/signIn")
            //    .usernameParameter("userId")
            //    .passwordParameter("password")
            //    .failureForwardUrl("/test")
            //    .permitAll()
            //}.logout{logout -> logout.logoutUrl("/push")
            //   .permitAll()
            //}

        return http.build()
    }


}
