package com.example.eyeserver.chat.dto

import com.google.gson.Gson

data class ChatMessage(
        val type: MessageType,
        val roomId: String,
        val sender: String,
        var message: Map<String, Any>,
        val people : Int,
) {
    // 메시지 타입 : 입장, 채팅
    enum class MessageType {
        ENTER,
        TALK,
        EXIT,
    }

    companion object {
        // JSON 문자열을 ChatMessage 객체로 변환하는 정적 메서드
        fun fromJson(jsonString: String): ChatMessage {
            return Gson().fromJson(jsonString, ChatMessage::class.java)
        }
    }
}
