package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
@Schema(description = "관리자 확인 DTO")
class TokenAndRoomResponseDTO (
    @Schema(title = "JWT토큰")
    var token : String,
    @Schema(title = "만료일자")
    var utcExpirationDate : Date,
    @Schema(title = "관리자 방 번호")
    var room : String,
    @Schema(title = "API 호출 성공 여부")
    var success : Boolean,
){

}