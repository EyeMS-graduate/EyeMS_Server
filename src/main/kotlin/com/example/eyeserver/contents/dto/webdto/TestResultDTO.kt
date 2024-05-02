package com.example.eyeserver.contents.dto.webdto

import com.example.eyeserver.contents.domain.UserContents
import java.time.LocalDate

data class TestResultDTO (
    val userId : String,

    val fixCount : Double,

    val saccade : Double,

    val totalReadTime : Double,

    val accurate : Double,

    val regression : Double,

    val questionTime : Double,

    val date : LocalDate,
)