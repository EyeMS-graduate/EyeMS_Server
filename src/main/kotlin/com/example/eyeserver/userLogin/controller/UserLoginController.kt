package com.example.eyeserver.userLogin.controller

import com.example.eyeserver.userLogin.dto.LoginRequestDTO
import com.example.eyeserver.userLogin.dto.LoginResponseDTO
import com.example.eyeserver.userLogin.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserLoginController (val loginService: LoginService) {

    @PostMapping("/signin")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
      
        return ResponseEntity.ok().body(loginService.login(loginRequestDTO))

    }
}