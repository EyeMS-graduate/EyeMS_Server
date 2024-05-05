package com.example.eyeserver.contents.dto.webdto

import com.example.eyeserver.contents.domain.UserContents
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 content 날짜 검색 요청 DTO")
class RequestContentBetweenDateDTO (
    val agencyId : String,
    val startDate : String = "",
    val endDate : String = "",
    val contentName : UserContents.ContentsName,
)