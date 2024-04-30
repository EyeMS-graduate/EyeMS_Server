package com.example.eyeserver.contents.dto.webdto

import com.example.eyeserver.contents.domain.UserContents
import java.time.LocalDate

data class ContentResultDTO (
    val userId : String,

    val contentName : UserContents.ContentsName,

    val score : Double,

    val date : LocalDate,
    )