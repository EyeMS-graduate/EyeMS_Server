package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.unitydto.*
import com.example.eyeserver.contents.service.UnityContentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Unity test 및 content API", description = "Unity에서 진행한 test와 content 결과물을 저장하고, 저장한 데이터들을 다시 가져오는 API 모음")
@RestController
@RequestMapping("/unity")
class UnityController (
    val unityContentService: UnityContentService,
){
    @Operation(summary = "content 저장", description = "Unity에서 Content를 완료 한 후에 저장하기 위한 API")
    @PostMapping("/contentdata")
    fun saveContent(@RequestBody requestUserContentDTO: RequestUserContentDTO) : ResponseEntity<ResponseUserContentDTO>{
        val result =  unityContentService.saveUserContentScore(requestUserContentDTO)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "content 요약", description = "유저 id 를 받을 시 해당 유저에 대한 content 기록을 제공함")
    @GetMapping("/summerycontent/{userId}")
    fun contentShowOrderByDate(@PathVariable userId : String) : ResponseEntity<ResponseSummaryContentDTO>{
        val result = unityContentService.getUserContentSummary(userId)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "test 요약", description = "유저 id 를 받을 시 해당 유저에 대한 test 기록을 제공함")
    @GetMapping("/summerytest/{userId}")
    fun testSummary(@PathVariable userId : String) : ResponseEntity<ResponseSummaryTestDTO>{
        val result = unityContentService.getUserTestSummary(userId)
        return ResponseEntity.ok().body(result)
    }

    @Operation(summary = "content 총 횟수 제공", description = "유저 id 를 받을 시 해당 유저에 대한 각각의 content 횟수를 반환")
    @GetMapping("/contentcount/{userId}")
    fun contentCountWithDate(@PathVariable userId : String) : ResponseEntity<ResponseDateWithCountDTO>{
        val result = unityContentService.getAllDateUserContentCount(userId)
        return ResponseEntity.ok().body(result)
    }
}