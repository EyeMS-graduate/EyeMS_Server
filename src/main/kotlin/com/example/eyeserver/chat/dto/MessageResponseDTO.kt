package com.example.eyeserver.chat.dto

data class MessageResponseDTO(
    val sender : String,
    var message : Map<String, Any>,
) {

}