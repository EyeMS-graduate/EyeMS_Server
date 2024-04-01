package com.example.eyeserver.chat.service

import com.example.eyeserver.chat.dto.ChatMessage
import com.example.eyeserver.chat.dto.ChatRoom
import com.example.eyeserver.chat.dto.MessageResponseDTO
import com.example.eyeserver.userLogin.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap


@Service
class ChatService(private val objectMapper: ObjectMapper,) {

    private val logger = LoggerFactory.getLogger(ChatService::class.java)
    private val chatRooms: MutableMap<String, ChatRoom> = LinkedHashMap()
    private val messageBuffer : MutableMap<String, MutableList<MessageResponseDTO>> = LinkedHashMap()


    init {
        init()
    }

    private fun init() {
        chatRooms.clear()
    }

    fun setRoom(roomId : String) : ChatRoom?{
        val chatRoom = ChatRoom(roomId = roomId, name = roomId)
       if (chatRooms.containsKey("nothing")){
           chatRooms[roomId] = chatRoom
           chatRooms.remove("nothing")
       }
        return chatRoom
    }

    fun findAllRoom(): List<ChatRoom> {
        return ArrayList(chatRooms.values)
    }

    fun findRoomById(roomId: String): ChatRoom? {
        return chatRooms[roomId]
    }

    fun createRoom(name: String): ChatRoom {
        val chatRoom = ChatRoom(roomId = name, name = name)
        chatRooms[name] = chatRoom
        return chatRoom
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        try {
            session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
        } catch (e: IOException) {
            logger.error(e.message, e)
        }
    }

    fun getSessionCount(): Int {
        return chatRooms.values.sumBy { it.sessions.size }
    }

    fun messageHandler(chatMessage: ChatMessage) : MutableList<MessageResponseDTO>{
        var result : MutableList<MessageResponseDTO> = mutableListOf()
        when(chatMessage.type){
            ChatMessage.MessageType.ENTER -> {
                if(!messageBuffer.containsKey(chatMessage.roomId)){
                    messageBuffer[chatMessage.roomId] = mutableListOf()
                }
                else{
                    messageBuffer[chatMessage.roomId]?.add(MessageResponseDTO(sender = chatMessage.sender, message = chatMessage.message))
                    result = messageBuffer[chatMessage.roomId]!!
                    return result
                }
            }
            ChatMessage.MessageType.TALK -> {
                if(messageBuffer[chatMessage.roomId]?.size!! > 100){
                    messageBuffer[chatMessage.roomId]?.clear()
                }
            }
            ChatMessage.MessageType.EXIT -> {
                //누가 나갔는지 확인하는 코드 작성
            }
        }
        val message = MessageResponseDTO(sender = chatMessage.sender, message = chatMessage.message)
        result.add(message)
        messageBuffer[chatMessage.roomId]?.add(message)
        return result
    }
}
