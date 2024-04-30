package com.example.eyeserver.contents.dto.unitydto

import com.example.eyeserver.contents.domain.UserContents

data class RequestUserContentDTO (
    val userId : String,

    val contentName : UserContents.ContentsName,

    val score : Double,

    val originScore : Double,
){
}