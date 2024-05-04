package com.example.eyeserver.contents.dto.unitydto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
@Schema(description = "유저 각 content 횟수 DTO 제공")
data class ResponseDateWithCountDTO (
    val date : MutableList<LocalDate>,
    val count : MutableList<Int>,
)