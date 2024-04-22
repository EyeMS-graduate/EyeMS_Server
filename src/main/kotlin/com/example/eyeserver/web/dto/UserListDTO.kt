package com.example.eyeserver.web.dto

data class UserListDTO (
    val success : Boolean,
    val userList : MutableList<String>,
)