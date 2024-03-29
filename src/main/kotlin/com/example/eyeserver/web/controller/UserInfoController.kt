package com.example.eyeserver.web.controller

import com.example.eyeserver.security.JwtTokenCheck
import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.web.dto.UserIdDTO
import com.example.eyeserver.web.dto.UserInfoDTO
import com.example.eyeserver.web.service.UserInfoService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserInfoController (
    private val userInfoService: UserInfoService,
    private val jwtTokenCheck: JwtTokenCheck,
){
    @GetMapping("/userlist")
    fun userList(httpServletRequest: HttpServletRequest) : ResponseEntity<MutableList<String>> {
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }
        val userNameList = userInfoService.userList(result[1])

        return ResponseEntity.ok(userNameList)
    }


    @PostMapping("/delete")
    fun deleteUser(httpServletRequest: HttpServletRequest, @RequestBody userIdDTO: UserIdDTO) : ResponseEntity<String>{
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        userInfoService.deleteUser(userIdDTO.userId)
        return ResponseEntity.ok("정상적으로 삭제됨")
    }

    @PostMapping("/info")
    fun userInfo(httpServletRequest: HttpServletRequest, @RequestBody userIdDTO: UserIdDTO) : ResponseEntity<UserInfoDTO>{
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }
        val userInfo = userInfoService.userInfo(userIdDTO.userId)

        return ResponseEntity.ok(userInfo)
    }

    @PostMapping("/update")
    fun userUpdate(httpServletRequest: HttpServletRequest, @RequestBody userInfoDTO: UserInfoDTO) : ResponseEntity<String>{
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body("메니저가 아닙니다")
        }
        userInfoService.updateUserInfo(userInfoDTO)

        return ResponseEntity.ok("업데이트 완료")
    }
}