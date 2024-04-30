package com.example.eyeserver.contents.dto.unitydto

import java.time.LocalDate

data class ResponseDateWithCountDTO (
    val date : MutableList<LocalDate>,
    val count : MutableList<Int>,
)