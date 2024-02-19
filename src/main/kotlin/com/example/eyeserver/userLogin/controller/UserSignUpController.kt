package com.example.eyeserver.userLogin.controller

import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.userLogin.dto.SignUpUserDTO
import com.example.eyeserver.userLogin.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserSignUpController (
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService
){
    @PostMapping("/addUser")
    fun addUser(httpServletRequest: HttpServletRequest, @RequestBody signUpUserDTO: SignUpUserDTO) : ResponseEntity<String>{

        val token = jwtTokenProvider.resolveToken(httpServletRequest)
        print(token.toString())

        if(token == null || !token.startsWith("Bearer ")){
            return ResponseEntity.badRequest().body("Invalid token")
        }

        val jwtToken = token.split(" ")[1].trim()

        if(!jwtTokenProvider.validateToken(jwtToken)){
            return ResponseEntity.badRequest().body("Invalid token")
        }

        val userId = jwtTokenProvider.userPrimaryKey(jwtToken)["userId"].toString()

        if(userId == signUpUserDTO.userId){
            return ResponseEntity.badRequest().body("Invalid token")
        }

        if(!userService.addUser(signUpUserDTO, jwtTokenProvider.userPrimaryKey(jwtToken)["agency"].toString())){
            return ResponseEntity.badRequest().body("exist userId")
        }


        return ResponseEntity.ok("good")
    }
}