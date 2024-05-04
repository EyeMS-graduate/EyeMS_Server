package com.example.eyeserver.contents.dto.unitydto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 content 평균 및 최근 데이터 DTO 제공" )
data class ResponseSummaryContentDTO (
    val latest : MutableList<Double>,
    val originLatest : MutableList<Double>,
    val now : MutableList<Double>,
)