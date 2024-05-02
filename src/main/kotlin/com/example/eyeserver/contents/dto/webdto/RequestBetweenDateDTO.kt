package com.example.eyeserver.contents.dto.webdto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate


data class RequestBetweenDateDTO (
    val startDate : String = "",
    val endDate : String = "",
    val userId : String = "",
)