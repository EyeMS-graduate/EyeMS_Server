package com.example.eyeserver.chat.dto

import com.google.gson.Gson

data class ChatMessage(
        val type: MessageType,
        val roomId: String,
        val sender: String,
        var message: Map<String, Any>,
        val people : PeopleType,
) {
    // 메시지 타입 : 입장, 채팅
    enum class MessageType {
        ENTER,
        TALK,
        EXIT,
    }

    // 권한 구분
    enum class PeopleType {
        PARENT,
        KID
    }
}
