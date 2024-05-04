package com.example.eyeserver.contents.dto.webdto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate


@Schema(description = "유저 content 날짜 검색 요청 DTO")
data class RequestBetweenDateDTO (
    val startDate : String = "",
    val endDate : String = "",
    val userId : String = "",
)