package com.example.eyeserver.userLogin.controller

import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO
import com.example.eyeserver.userLogin.dto.LoginDTO
import com.example.eyeserver.userLogin.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/unity")
class UserLoginController (val loginService: LoginService) {

    @PostMapping("/login")
    fun login(@RequestBody loginDTO: LoginDTO): ResponseEntity<TokenResponseDTO> {

        return ResponseEntity.ok().body(loginService.login(loginDTO))

    }
}