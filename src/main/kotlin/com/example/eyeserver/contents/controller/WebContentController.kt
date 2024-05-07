package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.*
import com.example.eyeserver.contents.dto.webdto.*
import com.example.eyeserver.contents.service.WebContentService
import com.example.eyeserver.security.JwtTokenCheck
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "react에서 사용되는 API",
    description = "모든 결과물을 react에서 표현하기 위해 제공되는 API")
@RestController
@RequestMapping("/user")
class WebContentController (
    private val webContentService: WebContentService,
    private val jwtTokenCheck: JwtTokenCheck,

){
    @Operation(summary = "content 요약", description = "userId를 주면 해당 유저의 content 플레이 결과 상위 5개 제공")
    @GetMapping("/summerycontent/{userId}")
    fun contentShowOrderByDate(@PathVariable userId : String) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.dateShowOrderByDate(userId)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "content 횟수", description = "userId를 주면 해당 유저의 각 content 플레이 횟수 제공")
    @GetMapping("/countcontent/{userId}")
    fun allCountContent(@PathVariable userId : String) : ResponseEntity<ResponseContentCountDTO>{
        val result = webContentService.contentCounter(userId)
        return ResponseEntity.ok().body(result)
    }


    @Operation(summary = "최근 content 평균지표", description = "userId를 주면 해당 유저의 content 플레이 결과 평균 지표 제공")
    @GetMapping("/latestcontent/{userId}")
    fun latestAverageContent(@PathVariable userId : String) : ResponseEntity<ResponseLatestContentDTO>{
        val result = webContentService.latestContentAverage(userId)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "최근 test 평균지표", description = "userId를 주면 해당 유저의 test 플레이 결과 평균 지표 제공")
    @GetMapping("/latesttest/{userId}")
    fun latestAverageTest(@PathVariable userId : String) : ResponseEntity<ResponseLatestTestDTO>{
        val result = webContentService.latestTestALlAverage(userId)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "test 요약", description = "userId를 주면 해당 유저의 test 플레이 결과 상위 5개 제공")
    @GetMapping("/summarytest/{userId}")
    fun testShowOrderByDate(@PathVariable userId: String) : ResponseEntity<MutableList<TestResultDTO>>{
        val result = webContentService.testDataOrderByDate(userId)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "test 전체", description = "userId를 주면 해당 유저의 test 플레이 결과 전체 제공")
    @GetMapping("/summarytestall")
    fun allTestShowOrderByDate(httpServletRequest: HttpServletRequest) : ResponseEntity<MutableList<TestResultDTO>>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok().body(webContentService.allTestData(result[1]))
    }

    @Operation(summary = "test 날짜 검색", description = "userId, 시작일, 종료일을 주면 해당 유저의 test 플레이 결과 제공")
    @PostMapping("/betweentest")
    fun testShowBetweenByDate(@RequestBody requestTestBetweenDateDTO: RequestTestBetweenDateDTO) : ResponseEntity<MutableList<TestResultDTO>>{
        val result = webContentService.testDateShowBetweenDate(requestTestBetweenDateDTO)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "content 날짜 검색", description = "시작일, 종료일, content이름, 기관 아이디를 주면 해당 유저의 content 플레이 결과 제공")
    @PostMapping("/betweencontent")
    fun contentShowBetweenByDate(@RequestBody requestContentBetweenDateDTO: RequestContentBetweenDateDTO) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = webContentService.contentDateShowBetweenDate(requestContentBetweenDateDTO)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "content 전체", description = "userId를 주면 해당 유저의 content 플레이 결과 전체 제공")
    @GetMapping("/summarycontentall")
    fun allContentShowOrderByDate(httpServletRequest: HttpServletRequest) : ResponseEntity<MutableList<ContentResultDTO>>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok().body(webContentService.allContentData(result[1]))
    }



}