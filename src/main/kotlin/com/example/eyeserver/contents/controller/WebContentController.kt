package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.*
import com.example.eyeserver.contents.dto.webdto.ContentResultDTO
import com.example.eyeserver.contents.dto.webdto.RequestBetweenDateDTO
import com.example.eyeserver.contents.dto.webdto.ResponseContentCountDTO
import com.example.eyeserver.contents.dto.webdto.ResponseLatestContentDTO
import com.example.eyeserver.contents.service.WebContentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class WebContentController (
    private val webContentService: WebContentService,

){
    @GetMapping("/summerycontent/{userId}")
    fun contentShowOrderByDate(@PathVariable userId : String) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.dateShowOrderByDate(userId)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/betweencontent")
    fun contentShowBetweenByDate(@RequestBody requestBetweenDateDTO: RequestBetweenDateDTO) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.dateShowBetweenDate(requestBetweenDateDTO)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/countcontent/{userId}")
    fun allCountContent(@PathVariable userId : String) : ResponseEntity<ResponseContentCountDTO>{
        val result = webContentService.contentCounter(userId)
        return ResponseEntity.ok().body(result)
    }


    @GetMapping("/latest/{userId}")
    fun latestAverageContent(@PathVariable userId : String) : ResponseEntity<ResponseLatestContentDTO>{
        val result = webContentService.latestAverage(userId)
        return ResponseEntity.ok().body(result)
    }




}