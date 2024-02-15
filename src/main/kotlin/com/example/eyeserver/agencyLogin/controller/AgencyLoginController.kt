package com.example.eyeserver.agencyLogin.controller

import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO
import com.example.eyeserver.agencyLogin.dto.AgencySignInDTO
import com.example.eyeserver.agencyLogin.dto.AgencySignUpDTO
import com.example.eyeserver.agencyLogin.service.AgencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AgencyLoginController (
    @Autowired private val agencyService: AgencyService
){

    @PostMapping("/signUp")
    fun register(@RequestBody userDTO: AgencySignUpDTO) : AgencySignUpDTO{
        val user =  agencyService.signUp(userDTO)
        return user
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody agencySignInDTO: AgencySignInDTO) : ResponseEntity<TokenResponseDTO>{

        return ResponseEntity.ok().body(agencyService.signIn(agencySignInDTO))

    }

    @GetMapping("/test")
    fun add() : String{
        return "hello"
    }

}