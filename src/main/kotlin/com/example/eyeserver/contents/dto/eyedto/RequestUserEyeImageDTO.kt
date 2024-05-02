package com.example.eyeserver.contents.dto.eyedto

import org.bson.types.Binary

data class RequestUserEyeImageDTO (
    val userId : String,
    val image : List<Binary>,

    val fixCount : List<Double>,
    val saccade : List<Double>,
    val totalReadTime : List<Double>,
    val accurate : List<Double>,
    val regression : List<Double>,
    val questionTime : List<Double>,

    val dyslexiaScore : List<Double>,
)