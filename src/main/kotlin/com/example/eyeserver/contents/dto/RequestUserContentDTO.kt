package com.example.eyeserver.contents.dto

import com.example.eyeserver.contents.domain.UserContents

data class RequestUserContentDTO (
    val userId : String,

    val contentName : UserContents.ContentsName,

    val score : Double,
){
}