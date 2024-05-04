package com.example.eyeserver.contents.dto.unitydto

import com.example.eyeserver.contents.domain.UserContents
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 content data 요청 DTO")
data class RequestUserContentDTO (
    val userId : String,

    @Schema(description = "컨텐츠 이름" , example = "Content1")
    val contentName : UserContents.ContentsName,

    val score : Double,

    val originScore : Double,
){
}