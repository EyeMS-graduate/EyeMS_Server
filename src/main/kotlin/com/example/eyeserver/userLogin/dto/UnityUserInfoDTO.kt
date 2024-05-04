package com.example.eyeserver.userLogin.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "unity에서의 유저 정보 제공 DTO")
data class UnityUserInfoDTO (
    @Schema(description = "유저 ID")
    val userId : String,
    @Schema(description = "유저 성함", example = "홍길동")
    val name : String,
    @Schema(description = "유저 생년월일" , example = "1999-05-05")
    val birth : String,
    @Schema(description = "유저 전화번호" , example = "010-1234-5678")
    val phone : String,
    @Schema(description = "유저 이메일" , example = "tttt@gmail.com")
    val email : String,
    @Schema(description = "유저 주소" , example = "경기도 어딘가")
    val address : String,
    @Schema(description = "유저 안경 착용 여부" , example = "yes, no")
    val glasses : String?,
    @Schema(description = "유저 성별" , example = "male, female")
    val gender : String?,
)