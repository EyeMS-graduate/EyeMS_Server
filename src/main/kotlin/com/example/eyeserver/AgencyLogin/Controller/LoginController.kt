package com.example.eyeserver.AgencyLogin.Controller

import com.example.eyeserver.AgencyLogin.DTO.TokenResponseDTO
import com.example.eyeserver.AgencyLogin.DTO.AgencyDTO
import com.example.eyeserver.AgencyLogin.Service.AgencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController (
    @Autowired private val agencyService: AgencyService
){

    @PostMapping("/signUp")
    fun register(@RequestBody userDTO: AgencyDTO) : AgencyDTO{
        val user =  agencyService.signUp(userDTO)
        return user
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody agencyDTO: AgencyDTO) : ResponseEntity<TokenResponseDTO>{

        return ResponseEntity.ok().body(agencyService.signIn(agencyDTO))

    }

    @GetMapping("/test")
    fun add() : String{
        return "hello"
    }

}