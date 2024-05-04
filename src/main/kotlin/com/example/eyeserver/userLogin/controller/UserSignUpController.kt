package com.example.eyeserver.userLogin.controller

import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.userLogin.dto.SignUpUserDTO
import com.example.eyeserver.userLogin.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "web 유저추가 API", description = "web에서의 유저 관련 API 모음")
@RestController
@RequestMapping("/user")
class UserSignUpController (
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService
){
    @Operation(summary = "유저 추가", description = "유저 정보와 토큰 제공시 추가")
    @PostMapping("/adduser")
    fun addUser(httpServletRequest: HttpServletRequest, @RequestBody signUpUserDTO: SignUpUserDTO) : ResponseEntity<String>{

        val token = jwtTokenProvider.resolveToken(httpServletRequest)

        if(token == null || !token.startsWith("Bearer ")){
            return ResponseEntity.badRequest().body("Invalid token")
        }

        val jwtToken = token.split(" ")[1].trim()

        if(!jwtTokenProvider.validateToken(jwtToken)){
            return ResponseEntity.badRequest().body("Invalid token")
        }

        val role = jwtTokenProvider.userPrimaryKey(jwtToken).subject
      
        if(role != "Manager"){
            return ResponseEntity.badRequest().body("님 매니저 아님")
        }

        if(!userService.addUser(signUpUserDTO, jwtTokenProvider.userPrimaryKey(jwtToken)["agency"].toString())){
            return ResponseEntity.badRequest().body("exist userId")
        }


        return ResponseEntity.ok("good")
    }

}