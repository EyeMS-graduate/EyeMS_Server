package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.RequestUserContentDTO
import com.example.eyeserver.contents.dto.ResponseUserContentDTO
import com.example.eyeserver.contents.service.UnityContentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/unity")
class UnityController (
    val unityContentService: UnityContentService,
){
    @PostMapping("/contentdata")
    fun saveContent(@RequestBody requestUserContentDTO: RequestUserContentDTO) : ResponseEntity<ResponseUserContentDTO>{
        val result =  unityContentService.saveUserContentScore(requestUserContentDTO)
        return ResponseEntity.ok(result)
    }
}