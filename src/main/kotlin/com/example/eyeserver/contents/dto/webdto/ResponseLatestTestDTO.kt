package com.example.eyeserver.contents.dto.webdto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 test 평균 및 최근 데이터 DTO 제공")
class ResponseLatestTestDTO (
    val latest : List<Double>,
    val now : List<Double>,
)