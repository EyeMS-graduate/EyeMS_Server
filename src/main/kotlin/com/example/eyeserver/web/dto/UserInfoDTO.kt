package com.example.eyeserver.web.dto

import java.time.LocalDate

class UserInfoDTO (
    val userId : String,
    val password : String,
    val name : String,
    val birth : String,
    val phone : String,
    val email : String,
    val address : String,
    val glasses : String?,
    val gender : String?,
    val date : String,
)