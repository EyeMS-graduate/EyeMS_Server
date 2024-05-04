package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column

@Schema(description = "관리자 회원가입 DTO")
data class AgencySignUpDTO (
    @Schema(description = "관리자 ID")
    val agencyId : String,
    @Schema(description = "관리자 비밀번호")
    val password : String,
    @Schema(description = "관리자 기관명", example = "XX시 동사무소")
    val agencyName : String,
    @Schema(description = "관리자 성함", example = "홍길동")
    val name : String,
    @Schema(description = "관리자 전화번호" , example = "010-1234-5678")
    val phone : String,
    @Schema(description = "관리자 생년월일" , example = "1999-05-05")
    val birth : String,
    @Schema(description = "관리자 주소" , example = "경기도 어딘가")
    val address : String,
    @Schema(description = "관리자 성별" , example = "male, female")
    val gender : String,
    @Schema(description = "관리자 이메일" , example = "tttt@gmail.com")
    val email : String,
)