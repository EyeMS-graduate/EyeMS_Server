package com.example.eyeserver.contents.dto.webdto

import jakarta.persistence.Column

class RequestUserTestDTO (
    val userId : String,

    val fixCount : Double,

    val saccade : Double,

    val totalReadTime : Double,

    val accurate : Double,

    val regression : Double,

    val questionTime : Double,
)