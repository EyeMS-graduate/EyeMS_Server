package com.example.eyeserver.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Arrays


@Configuration
class SwaggerConfig() {

    @Bean
    fun openAPI() : OpenAPI{
        val info = Info().version("v1.0.0")
            .title("시선처리 특성 분석 기반의 난독증 추정 및 개선 방법에 관한 연구 API")
            .description("시선처리 관리하기 위한 API 설계")
        val securityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER).name("Authorization")
        val securityRequirement = SecurityRequirement().addList("bearerAuth")

        return OpenAPI().info(info)
            .components(
                Components().addSecuritySchemes("bearerAuth", securityScheme))
            .security(listOf(securityRequirement))

    }

    @Bean
    fun userGroup() : GroupedOpenApi{
        return GroupedOpenApi.builder().group("유저 관리")
            .packagesToScan("com.example.eyeserver.userLogin", "com.example.eyeserver.web").build()
    }

    @Bean
    fun agencyGroup() : GroupedOpenApi{
        return GroupedOpenApi.builder().group("관리자")
            .packagesToScan("com.example.eyeserver.agency").build()
    }

    @Bean
    fun socketGroup() : GroupedOpenApi{
        return GroupedOpenApi.builder().group("소켓")
            .packagesToScan("com.example.eyeserver.chat").build()
    }

    @Bean
    fun contentGroup() : GroupedOpenApi{
        return GroupedOpenApi.builder().group("검사 결과 및 개선 API")
            .packagesToScan("com.example.eyeserver.contents").build()
    }

}