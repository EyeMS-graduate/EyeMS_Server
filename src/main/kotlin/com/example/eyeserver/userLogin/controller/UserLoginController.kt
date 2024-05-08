package com.example.eyeserver.userLogin.controller

import com.example.eyeserver.security.JwtTokenCheck
import com.example.eyeserver.userLogin.dto.LoginRequestDTO
import com.example.eyeserver.userLogin.dto.LoginResponseDTO
import com.example.eyeserver.userLogin.dto.UnityUserInfoDTO
import com.example.eyeserver.userLogin.service.LoginService
import com.example.eyeserver.userLogin.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Unity login 및 정보수정 API", description = "Unity에서 로그인 시 진행되는 API 모음")
@RestController
@RequestMapping("/user")
class UserLoginController (
    val loginService: LoginService,
    val userService : UserService,
    val jwtTokenCheck: JwtTokenCheck
) {

    @Operation(summary = "Unity login", description = "Unity 로그인 API")
    @PostMapping("/signin")
    fun userSignIn(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
      
        return ResponseEntity.ok().body(loginService.login(loginRequestDTO))

    }

    @Operation(summary = "Unity info check", description = "Unity 첫 로그인시 정보 제공 API")
    @GetMapping("/unityInfo")
    fun userUnityInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<Any>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)
        if(result?.get(0) != "User"){
            return ResponseEntity.badRequest().body("권한이 없습니다.")
        }

        return ResponseEntity.ok(userService.userInfo(result?.get(1)!!))
    }

    @Operation(summary = "Unity info update", description = "Unity 첫 정보수정 API")
    @PostMapping("/unityupdate")
    fun userUnityUpdate(@RequestBody unityUserInfoDTO: UnityUserInfoDTO) : ResponseEntity<Boolean> {
        val result = userService.unityUserUpdate(unityUserInfoDTO)
        return ResponseEntity.ok(result)
    }
}