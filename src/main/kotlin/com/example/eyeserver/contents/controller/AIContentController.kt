package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.webdto.RequestUserTestDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.service.AIContentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/machine")
class AIContentController(
    val aiContentService: AIContentService,
) {

    @PostMapping("/testdata")
    fun saveTestData(@RequestBody requestUserTestDTO: RequestUserTestDTO) : ResponseEntity<ResponseUserContentDTO>{
        val result = aiContentService.saveTestContent(requestUserTestDTO)
        return ResponseEntity.ok().body(result)
    }
}