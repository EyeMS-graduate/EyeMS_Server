package com.example.eyeserver.agencyLogin.controller

import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO
import com.example.eyeserver.agencyLogin.dto.AgencySignInDTO
import com.example.eyeserver.agencyLogin.dto.AgencySignUpDTO
import com.example.eyeserver.agencyLogin.service.AgencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/agency")
class AgencyLoginController (
    @Autowired private val agencyService: AgencyService
){

    @PostMapping("/signup")
    fun register(@RequestBody userDTO: AgencySignUpDTO) : ResponseEntity<AgencySignUpDTO>{
        return ResponseEntity.ok().body(agencyService.signUp(userDTO))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody agencySignInDTO: AgencySignInDTO) : ResponseEntity<TokenResponseDTO>{
        return ResponseEntity.ok().body(agencyService.signIn(agencySignInDTO))
    }

}