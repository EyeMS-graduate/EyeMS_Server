package com.example.eyeserver.userLogin.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "unity 로그인 성공 시 제공")
class LoginResponseDTO (
    @Schema(description = "JWT 토큰")
    val token: String,
    @Schema(description = "첫 로그인 여부 확인")
    val visited: Boolean
)