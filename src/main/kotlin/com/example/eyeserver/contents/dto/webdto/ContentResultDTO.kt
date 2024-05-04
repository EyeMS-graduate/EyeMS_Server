package com.example.eyeserver.contents.dto.webdto

import com.example.eyeserver.contents.domain.UserContents
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "유저 content data 제공 DTO")
data class ContentResultDTO (
    val userId : String,

    val contentName : UserContents.ContentsName,

    val score : Double,

    val date : LocalDate,
    )