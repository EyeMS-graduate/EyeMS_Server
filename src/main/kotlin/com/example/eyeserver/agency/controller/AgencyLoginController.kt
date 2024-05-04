package com.example.eyeserver.agency.controller

import com.example.eyeserver.agency.dto.AgencySignInDTO
import com.example.eyeserver.agency.dto.AgencySignUpDTO
import com.example.eyeserver.agency.dto.TokenAndRoomResponseDTO
import com.example.eyeserver.agency.service.AgencyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "관리자 Auth", description = "관리자 회원가입 및 로그인 담당")
@RestController
@RequestMapping("/agency")
class AgencyLoginController (
    @Autowired private val agencyService: AgencyService
){

    @Operation(summary = "관리자 회원 가입")
    @PostMapping("/signup")
    fun agencySignUp(@RequestBody userDTO: AgencySignUpDTO): ResponseEntity<AgencySignUpDTO>{
        val result = agencyService.signUp(userDTO)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "관리자 로그인", description = "로그인 성공 시 토큰과 유효기간 지급")
    @PostMapping("/signin")
    fun agencySignIn(@RequestBody agencySignInDTO: AgencySignInDTO) : ResponseEntity<TokenAndRoomResponseDTO>{
        return ResponseEntity.ok().body(agencyService.signIn(agencySignInDTO))
    }

}