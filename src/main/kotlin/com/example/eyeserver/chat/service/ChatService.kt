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
class ChatService(private val objectMapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(ChatService::class.java)
    private val chatRooms: MutableMap<String, ChatRoom> = LinkedHashMap()
    private val messageBuffer: MutableMap<String, MutableList<MessageResponseDTO>> = LinkedHashMap()
    private val userList: MutableSet<String> = mutableSetOf()


    init {
        init()
    }

    private fun init() {
        chatRooms.clear()
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

    fun messageHandler(chatMessage: ChatMessage): MutableList<MessageResponseDTO> {
        var result: MutableList<MessageResponseDTO> = mutableListOf()
        when (chatMessage.type) {
            ChatMessage.MessageType.ENTER -> {
                userList.add(chatMessage.sender)

                if (!messageBuffer.containsKey(chatMessage.roomId)) {
                    if (chatMessage.people == ChatMessage.PeopleType.PARENT) {
                        userList.remove(chatMessage.sender)
                    }
                    messageBuffer[chatMessage.roomId] = mutableListOf()
                    return mutableListOf()

                } else {

                    val senderMap = mutableMapOf<String, Any>()
                    if (chatMessage.people == ChatMessage.PeopleType.PARENT) {
                        userList.remove(chatMessage.sender)
                    }
                    senderMap["list"] = userList
                    result.add(
                        MessageResponseDTO(
                            sender = chatMessage.sender,
                            message = senderMap
                        )
                    )
                    return result
                }
            }

            ChatMessage.MessageType.TALK -> {
                if (messageBuffer[chatMessage.roomId]?.size!! > 5000) {
                    messageBuffer[chatMessage.roomId]?.clear()
                }
                if (chatMessage.people == ChatMessage.PeopleType.PARENT) {
                    return agencyEnterUser(chatMessage)
                }
            }

            ChatMessage.MessageType.EXIT -> {

                val iterator = messageBuffer[chatMessage.roomId]?.iterator()
                while (iterator?.hasNext() == true) {
                    val message = iterator.next()
                    if (message.sender == chatMessage.sender) {
                        iterator.remove()
                    }
                }


                userList.remove(chatMessage.sender)
                val senderMap = mutableMapOf<String, Any>()
                senderMap["list"] = userList
                result.add(
                    MessageResponseDTO(
                        sender = chatMessage.sender,
                        message = senderMap
                    )
                )

                return result
            }
        }
        val message = MessageResponseDTO(sender = chatMessage.sender, message = chatMessage.message)
        result.add(message)
        messageBuffer[chatMessage.roomId]?.add(message)
        return result
    }

    fun agencyEnterUser(chatMessage: ChatMessage): MutableList<MessageResponseDTO> {
        val senderMessage = mutableListOf<MessageResponseDTO>()
        for (i in messageBuffer[chatMessage.roomId]!!) {
            if (i.sender == chatMessage.sender) {
                senderMessage.add(MessageResponseDTO(chatMessage.sender, i.message))
            }
        }
        return senderMessage

    }
}
