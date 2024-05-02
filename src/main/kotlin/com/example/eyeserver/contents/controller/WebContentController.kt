package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.*
import com.example.eyeserver.contents.dto.webdto.*
import com.example.eyeserver.contents.service.WebContentService
import com.example.eyeserver.security.JwtTokenCheck
import jakarta.servlet.http.HttpServletRequest
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
    private val jwtTokenCheck: JwtTokenCheck,

){
    @GetMapping("/summerycontent/{userId}")
    fun contentShowOrderByDate(@PathVariable userId : String) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.dateShowOrderByDate(userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/countcontent/{userId}")
    fun allCountContent(@PathVariable userId : String) : ResponseEntity<ResponseContentCountDTO>{
        val result = webContentService.contentCounter(userId)
        return ResponseEntity.ok().body(result)
    }


    @GetMapping("/latestcontent/{userId}")
    fun latestAverageContent(@PathVariable userId : String) : ResponseEntity<ResponseLatestContentDTO>{
        val result = webContentService.latestAverage(userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/latesttest/{userId}")
    fun latestAverageTest(@PathVariable userId : String) : ResponseEntity<ResponseLatestTestDTO>{
        val result = webContentService.latestTestALlAverage(userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/summarytest/{userId}")
    fun testShowOrderByDate(@PathVariable userId: String) : ResponseEntity<MutableList<TestResultDTO>>{
        val result = webContentService.testDataOrderByDate(userId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping("/summarytestall")
    fun allTestShowOrderByDate(httpServletRequest: HttpServletRequest) : ResponseEntity<MutableList<TestResultDTO>>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok().body(webContentService.allTestData(result[1]))
    }

    @PostMapping("/betweentest")
    fun contentShowBetweenByDate(@RequestBody requestBetweenDateDTO: RequestBetweenDateDTO) : ResponseEntity<MutableList<TestResultDTO>>{
        print("hello")
        val result = webContentService.testDateShowBetweenDate(requestBetweenDateDTO)
        return ResponseEntity.ok().body(result)
    }



}