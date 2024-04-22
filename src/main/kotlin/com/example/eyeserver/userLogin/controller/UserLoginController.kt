package com.example.eyeserver.userLogin.controller

import com.example.eyeserver.security.JwtTokenCheck
import com.example.eyeserver.userLogin.dto.LoginRequestDTO
import com.example.eyeserver.userLogin.dto.LoginResponseDTO
import com.example.eyeserver.userLogin.dto.UnityUserInfoDTO
import com.example.eyeserver.userLogin.service.LoginService
import com.example.eyeserver.userLogin.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserLoginController (
    val loginService: LoginService,
    val userService : UserService,
    val jwtTokenCheck: JwtTokenCheck
) {

    @PostMapping("/signin")
    fun userSignIn(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
      
        return ResponseEntity.ok().body(loginService.login(loginRequestDTO))

    }

    @GetMapping("/unityInfo")
    fun userUnityInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<Any>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)
        if(result?.get(0) != "User"){
            return ResponseEntity.badRequest().body("권한이 없습니다.")
        }

        return ResponseEntity.ok(userService.userInfo(result?.get(1)!!))
    }

    @PostMapping("/unityupdate")
    fun userUnityUpdate(unityUserInfoDTO: UnityUserInfoDTO) : ResponseEntity<Boolean> {
        val result = userService.unityUserUpdate(unityUserInfoDTO)
        return ResponseEntity.ok(result)
    }
}