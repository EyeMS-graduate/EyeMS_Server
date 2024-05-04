package com.example.eyeserver.web.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "web에서의 유저 목록 제공 DTO")
data class UserListDTO (
    @Schema(description = "성공 여부")
    val success : Boolean,
    @Schema(description = "유저 목록")
    val userList : MutableList<String>,
)