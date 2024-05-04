package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "관리자 정보 DTO")
class RequestAgencyInfoDTO (
    @Schema(description = "관리자 ID")
    val agencyId : String,
    @Schema(description = "관리자 비밀번호")
    val password : String,
    @Schema(description = "관리자 전화번호" , example = "010-1234-5678")
    val phone : String,
    @Schema(description = "관리자 이메일" , example = "tttt@gmail.com")
    val email : String,
    @Schema(description = "관리자 주소" , example = "경기도 어딘가")
    val address : String,

)