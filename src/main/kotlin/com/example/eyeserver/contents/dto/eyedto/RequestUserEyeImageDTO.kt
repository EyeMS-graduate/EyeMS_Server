package com.example.eyeserver.contents.dto.eyedto

import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.Binary

@Schema(description = "유저 test 결과 이미지 3개 DTO 요청")
data class RequestUserEyeImageDTO (
    val userId : String,
    val image : List<String>,

    val fixCount : List<Double>,
    val saccade : List<Double>,
    val totalReadTime : List<Double>,
    val accurate : List<Double>,
    val regression : List<Double>,
    val questionTime : List<Double>,

    val dyslexiaScore : List<Double>,
)