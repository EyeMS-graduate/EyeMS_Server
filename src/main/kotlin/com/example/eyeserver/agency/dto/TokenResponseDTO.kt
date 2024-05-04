package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "관리자 확인 DTO")
data class TokenResponseDTO (
    @Schema(title = "JWT토큰")
    var token : String,
    @Schema(title = "만료일자")
    var utcExpirationDate : Date,
)