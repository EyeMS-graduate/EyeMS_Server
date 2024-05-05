package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.eyedto.RequestUserEyeImageDTO
import com.example.eyeserver.contents.dto.eyedto.ResponseUserEyeImageDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.service.UserEyeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@Tag(name = "python에서 사용되는 API",
    description = "python에서 진행한 딥러닝 결과를 저장 및 제공함")
@RestController
@RequestMapping("/eye")
class UserEyeController(
    private val userEyeService: UserEyeService,
) {
    @Operation(summary = "test 결과 저장", description = "이미지 정보 및 유저 정보를 3개 저장함")
    @PostMapping("/save")
    fun saveImage(@RequestBody requestUserEyeImageDTO: RequestUserEyeImageDTO) : ResponseEntity<ResponseUserContentDTO>{
        return ResponseEntity.ok().body(userEyeService.saveUserImage(requestUserEyeImageDTO))
    }

    @Operation(summary = "test 결과 요약", description = "이미지 정보 및 유저 정보를 3개 다 보여준다.")
    @GetMapping("/showimage/{userId}/{date}")
    fun showThreeImage(@PathVariable userId : String, @PathVariable date : String) : ResponseEntity<ResponseUserEyeImageDTO>{
        return ResponseEntity.ok().body(userEyeService.getUserAllImage(userId, date))
    }


}
