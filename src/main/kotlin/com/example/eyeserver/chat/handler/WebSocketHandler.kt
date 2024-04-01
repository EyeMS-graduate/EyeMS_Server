package com.example.eyeserver.chat.handler

import com.example.eyeserver.agencyLogin.repository.AgencyRepository
import com.example.eyeserver.chat.dto.ChatMessage
import com.example.eyeserver.chat.dto.ChatRoom
import com.example.eyeserver.chat.service.ChatService
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler(
        private val objectMapper: ObjectMapper,
        private var chatService: ChatService,

) : TextWebSocketHandler() {

    private val logger = LoggerFactory.getLogger(WebSocketHandler::class.java)

    // 데이터를 모을 리스트를 초기화
    private val messageBuffer: MutableMap<String, MutableList<ChatMessage>> = HashMap()

    private val chatRoomSessionMap : MutableMap<String, WebSocketSession> = HashMap()


    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload: String = message.payload

        //logger.info("payload {}", payload)

        val chatMessage: ChatMessage = objectMapper.readValue(payload, ChatMessage::class.java)
        val room: ChatRoom? = chatService.findRoomById(chatMessage.roomId)

        if(chatMessage.type == ChatMessage.MessageType.TALK){
            // TALK 메시지인 경우에는 데이터를 buffer에 저장
            val roomId = chatMessage.roomId
            val buffer = messageBuffer.getOrDefault(roomId, mutableListOf())
            buffer.add(chatMessage)
            messageBuffer[roomId] = buffer
            room?.sessions?.forEach { roomSession ->
                if (roomSession != session) {
                    sendMessageToSession(roomSession, chatMessage)
                }
            }

        } else if (chatMessage.type == ChatMessage.MessageType.EXIT) {
            // EXIT 메시지인 경우에는 데이터를 출력
            val roomId = chatMessage.roomId
            val buffer = messageBuffer.remove(roomId) ?: mutableListOf()

            // 종료 메시지가 오면 데이터를 출력합니다.
            if (room != null && room.sessions.contains(session)) {
                for (msg in buffer) {
                    println("${msg.sender}: ${msg.message}")
                }

                room.sessions.remove(session)
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        // 여긴 추후 변경
        val chatRoom = chatService.createRoom("nothing")

        // sender 부분은 클라이언트에서 접속한 유저 부분으로 변경해야함.
        //val message = ChatMessage(ChatMessage.MessageType.ENTER, chatRoom!!.roomId, "Server", "Welcome to the chat room!")
        //val message = ChatMessage(ChatMessage.MessageType.ENTER, "nothing", "Server", , 1)
        //chatRoom.handleActions(session, message, chatService)
    }

    fun sendMessageToSession(session : WebSocketSession,chatMessage: ChatMessage){
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(chatMessage)))
    }
}