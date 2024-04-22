package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.ContentResultDTO
import com.example.eyeserver.contents.dto.RequestBetweenDateDTO
import com.example.eyeserver.contents.dto.RequestUserDataDTO
import com.example.eyeserver.contents.service.WebContentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class WebContentController (
    private val webContentService: WebContentService
){
    @GetMapping("/content")
    fun contentShowOrderByDate(@RequestBody requestUserDataDTO: RequestUserDataDTO) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.dateShowOrderByDate(requestUserDataDTO.userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/betweencontent")
    fun contentShowBetweenByDate(@RequestBody requestBetweenDateDTO: RequestBetweenDateDTO) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.dateShowBetweenDate(requestBetweenDateDTO)
        return ResponseEntity.ok().body(result)
    }


}