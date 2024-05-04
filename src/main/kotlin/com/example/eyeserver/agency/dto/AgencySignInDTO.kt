package com.example.eyeserver.agency.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "관리자 로그인 DTO")
class AgencySignInDTO (
    val agencyId : String,
    val password : String
)