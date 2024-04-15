package com.example.eyeserver.chat.controller

import com.example.eyeserver.chat.dto.ChatMessage
import com.example.eyeserver.chat.dto.ChatRoom
import com.example.eyeserver.chat.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@RestController
class ChatController(
    private val chatService: ChatService,
    private val template: SimpMessagingTemplate) {


    @PostMapping("/create")
    fun createRoom(@RequestParam name: String): ChatRoom {
        return chatService.createRoom(name)
    }

    @GetMapping("/findAll")
    fun findAllRoom(): List<ChatRoom> {
        return chatService.findAllRoom()
    }

    @GetMapping("/sessionCount")
    fun getSessionCount(): Int {
        return chatService.getSessionCount()
    }

    @PostMapping("/send")
    fun sendRoom(@RequestBody chatMessage : ChatMessage){
        val chatRoom = chatService.findRoomById(chatMessage.roomId)
        val message = ChatMessage(ChatMessage.MessageType.TALK, chatMessage.roomId, chatMessage.sender, chatMessage.message, ChatMessage.PeopleType.PARENT)
        chatRoom?.sendMessage(message, chatService)
    }


    @MessageMapping("/chat/message")
    fun message(chatMessage: ChatMessage){
        val result = chatService.messageHandler(chatMessage)
        if(chatMessage.type == ChatMessage.MessageType.ENTER || chatMessage.type == ChatMessage.MessageType.EXIT){
            template.convertAndSend("/sub/enter/chat/room/" + chatMessage.roomId, result)
        }
        else{
            template.convertAndSend("/sub/chat/room/" + chatMessage.roomId +"/" +chatMessage.sender, result)
        }

    }
}