package com.example.eyeserver.contents.dto.unitydto

data class ResponseSummaryContentDTO (
    val latest : MutableList<Double>,
    val originLatest : MutableList<Double>,
    val now : MutableList<Double>,
)