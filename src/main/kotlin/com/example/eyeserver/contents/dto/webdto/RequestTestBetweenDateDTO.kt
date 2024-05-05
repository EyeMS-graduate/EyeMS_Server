package com.example.eyeserver.contents.dto.webdto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "유저 test 날짜 검색 요청 DTO")
data class RequestTestBetweenDateDTO (
    val startDate : String = "",
    val endDate : String = "",
    val userId : String = "",
)