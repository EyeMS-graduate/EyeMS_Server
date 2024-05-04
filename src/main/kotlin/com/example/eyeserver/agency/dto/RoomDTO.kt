package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "관리자 방번호 DTO")
class RoomDTO (
    @Schema(title = "관리자 방번호")
    var room : String,
){

}