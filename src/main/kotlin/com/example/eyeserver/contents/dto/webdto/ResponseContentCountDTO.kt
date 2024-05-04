package com.example.eyeserver.contents.dto.webdto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 content 횟수 요청 DTO")
data class ResponseContentCountDTO (
    val content1 : Long,
    val content2 : Long,
    val content3 : Long,
    val content4 : Long,
    val content5 : Long,
    val content6 : Long,
)