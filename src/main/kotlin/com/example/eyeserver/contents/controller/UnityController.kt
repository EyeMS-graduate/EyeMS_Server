package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.unitydto.*
import com.example.eyeserver.contents.service.UnityContentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/summerycontent/{userId}")
    fun contentShowOrderByDate(@PathVariable userId : String) : ResponseEntity<ResponseSummaryContentDTO>{
        val result = unityContentService.getUserContentSummary(userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/summerytest/{userId}")
    fun testSummary(@PathVariable userId : String) : ResponseEntity<ResponseSummaryTestDTO>{
        val result = unityContentService.getUserTestSummary(userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/contentcount/{userId}")
    fun contentCountWithDate(@PathVariable userId : String) : ResponseEntity<ResponseDateWithCountDTO>{
        val result = unityContentService.getAllDateUserContentCount(userId)
        return ResponseEntity.ok().body(result)
    }
}