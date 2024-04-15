package com.example.eyeserver.userLogin.dto

data class UnityUserInfoDTO (
    val userId : String,
    val name : String,
    val birth : String,
    val phone : String,
    val email : String,
    val address : String,
    val glasses : String?,
    val gender : String?,
)