package com.example.eyeserver.Login.Controller

import com.example.eyeserver.Login.DTO.UserDTO
import com.example.eyeserver.Login.Domain.User
import com.example.eyeserver.Login.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController (
    @Autowired private val userService: UserService
){

    @PostMapping("/SignUp")
    fun register(@RequestBody userDTO: UserDTO) : UserDTO{
        print(userDTO.userId)
        print(userDTO.password)
        val user =  userService.signUp(userDTO)
        return user
    }

    @GetMapping("")
    fun add() : Int{
        return 1
    }

}