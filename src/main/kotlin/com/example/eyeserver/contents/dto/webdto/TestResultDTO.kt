package com.example.eyeserver.contents.dto.webdto

import com.example.eyeserver.contents.domain.UserContents
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "유저 test 데이터 DTO 제공")
data class TestResultDTO (
    val userId : String,

    val fixCount : Double,

    val saccade : Double,

    val totalReadTime : Double,

    val accurate : Double,

    val regression : Double,

    val questionTime : Double,

    val date : LocalDate,
)