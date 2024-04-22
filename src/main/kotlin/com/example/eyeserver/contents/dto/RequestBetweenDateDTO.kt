package com.example.eyeserver.contents.dto

import java.time.LocalDate

data class RequestBetweenDateDTO (
    val startDate : LocalDate,
    val endDate : LocalDate,
    val userId : String,
)