package com.example.eyeserver.contents.dto.unitydto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "성공 여부 및 관련 메세지 제공")
data class ResponseUserContentDTO (
    val success : Boolean,
    val message : String,
){
}