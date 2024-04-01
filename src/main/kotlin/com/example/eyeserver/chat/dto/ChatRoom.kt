package com.example.eyeserver.chat.dto

import com.example.eyeserver.chat.service.ChatService
import org.springframework.web.socket.WebSocketSession

class ChatRoom(
        val roomId: String,
        val name: String
) {
    public val sessions: MutableSet<WebSocketSession> = HashSet()

    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.type == ChatMessage.MessageType.ENTER) {
            sessions.add(session)
            //chatMessage.message = "${chatMessage.sender}님이 입장했습니다."
        }
        sendMessage(chatMessage, chatService)
    }

    fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream().forEach { session -> chatService.sendMessage(session, message) }
    }
}
