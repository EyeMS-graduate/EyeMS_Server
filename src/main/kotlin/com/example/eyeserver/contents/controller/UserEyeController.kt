package com.example.eyeserver.contents.controller

import com.example.eyeserver.contents.dto.eyedto.RequestUserEyeImageDTO
import com.example.eyeserver.contents.dto.eyedto.ResponseUserEyeImageDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.service.UserEyeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/eye")
class UserEyeController(
    private val userEyeService: UserEyeService,
) {
    @PostMapping("/save")
    fun saveImage(requestUserEyeImageDTO: RequestUserEyeImageDTO) : ResponseEntity<ResponseUserContentDTO>{
        return ResponseEntity.ok().body(userEyeService.saveUserImage(requestUserEyeImageDTO))
    }

    @GetMapping("/showimage/{userId}/{date}")
    fun showThreeImage(@PathVariable userId : String, @PathVariable date : String) : ResponseEntity<ResponseUserEyeImageDTO>{
        return ResponseEntity.ok().body(userEyeService.getUserAllImage(userId, date))
    }


}
