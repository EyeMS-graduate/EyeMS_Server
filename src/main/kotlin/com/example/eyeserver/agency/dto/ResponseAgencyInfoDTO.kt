package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.util.Date

@Schema(description = "관리자 정보 DTO")
data class ResponseAgencyInfoDTO (
    @Schema(description = "관리자 ID")
    val agencyId : String,
    @Schema(description = "관리자 성함", example = "홍길동")
    val name : String,
    @Schema(description = "관리자 기관명", example = "XX시 동사무소")
    val agencyName : String,
    @Schema(description = "관리자 전화번호" , example = "010-1234-5678")
    val phone : String,
    @Schema(description = "관리자 주소" , example = "경기도 어딘가")
    val address : String,
    @Schema(description = "관리자 생년월일" , example = "1999-05-05")
    val birth : String,
    @Schema(description = "관리자 성별" , example = "male")
    val gender : String,
    @Schema(description = "관리자 이메일" , example = "tttt@gmail.com")
    val email : String,
    @Schema(description = "관리자 첫 회원가입 일자" , example = "2000-05-08")
    val date : LocalDate,
)