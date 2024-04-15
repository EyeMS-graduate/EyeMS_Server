package com.example.eyeserver.agency.controller

import com.example.eyeserver.agency.dto.AgencySignInDTO
import com.example.eyeserver.agency.dto.AgencySignUpDTO
import com.example.eyeserver.agency.dto.TokenAndRoomResponseDTO
import com.example.eyeserver.agency.service.AgencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/agency")
class AgencyLoginController (
    @Autowired private val agencyService: AgencyService
){

    @PostMapping("/signup")
    fun agencySignUp(@RequestBody userDTO: AgencySignUpDTO): ResponseEntity<AgencySignUpDTO>{
        val result = agencyService.signUp(userDTO)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/signin")
    fun agencySignIn(@RequestBody agencySignInDTO: AgencySignInDTO) : ResponseEntity<TokenAndRoomResponseDTO>{
        return ResponseEntity.ok().body(agencyService.signIn(agencySignInDTO))
    }

}