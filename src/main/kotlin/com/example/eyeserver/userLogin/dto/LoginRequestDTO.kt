package com.example.eyeserver.userLogin.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "unity 로그인 시 정보 요구")
class LoginRequestDTO (
    val userId: String,
    val password: String
)