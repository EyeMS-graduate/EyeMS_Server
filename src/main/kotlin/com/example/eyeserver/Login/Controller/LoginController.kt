package com.example.eyeserver.Login.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("")
    fun add() : Int{
        return 1
    }

}