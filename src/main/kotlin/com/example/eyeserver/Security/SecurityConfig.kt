//package com.example.eyeserver.Security
//
//import com.example.eyeserver.Login.Role.Role
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer
//
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher
//import kotlin.jvm.Throws
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain{
//        http
//            .csrf{
//                    csrfConfig: CsrfConfigurer<HttpSecurity> -> csrfConfig.disable()
//            }
//            .headers { headerConfig: HeadersConfigurer<HttpSecurity?> ->
//                headerConfig.frameOptions(
//                    { frameOptionsConfig -> frameOptionsConfig.disable() }
//                )
//            } // 2번
//            .authorizeHttpRequests { authorizeRequests ->
//                authorizeRequests
//                    .requestMatchers(AntPathRequestMatcher("/h2-console/**")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/swagger-resources/**")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/favicon.ico")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/error")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/api/v1/client/**")).hasRole(Role.User.name)
//                    .requestMatchers(AntPathRequestMatcher("/api/v1/posts/**")).hasRole(Role.Manager.name)
//                    .requestMatchers(AntPathRequestMatcher("/api/v1/admins/**")).hasRole(Role.Admin.name)
//                    .anyRequest().authenticated()
//            }
//        return http.build()
//    } // 3번
//
//}
