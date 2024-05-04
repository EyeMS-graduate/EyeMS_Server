package com.example.eyeserver.web.controller

import com.example.eyeserver.security.JwtTokenCheck
import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.web.dto.UserIdDTO
import com.example.eyeserver.web.dto.UserInfoDTO
import com.example.eyeserver.web.dto.UserListDTO
import com.example.eyeserver.web.service.UserInfoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(name = "web 유저관리 API", description = "web에서 관리자가 user 관리하기 위한 API모음")
@RestController
@RequestMapping("/user")
class UserInfoController (
    private val userInfoService: UserInfoService,
    private val jwtTokenCheck: JwtTokenCheck,
){
    @Operation(summary = "현재 유저 목록 확인", description = "token을 주면 관리자가 속한 유저의 목록 확인 API")
    @GetMapping("/userlist")
    fun userList(httpServletRequest: HttpServletRequest) : ResponseEntity<UserListDTO> {
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }
        val userNameList = userInfoService.userList(result[1])

        return ResponseEntity.ok(userNameList)
    }

    @Operation(summary = "유저 검색", description = "token과 userId를 주면 userId가 포함된 유저목록 검색 API")
    @GetMapping("/user/{userId}")
    fun findUser(httpServletRequest: HttpServletRequest, @PathVariable userId : String) : ResponseEntity<UserListDTO>{
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }
        return if(userId == ""){
            ResponseEntity.ok().body(userInfoService.userList(result[1]))
        } else{
            ResponseEntity.ok().body(userInfoService.findUser(userId, result[1]))
        }

    }

    @Operation(summary = "유저 삭제", description = "token과 userId를 주면 유저 삭제")
    @PostMapping("/delete")
    fun deleteUser(httpServletRequest: HttpServletRequest, @RequestBody userIdDTO: UserIdDTO) : ResponseEntity<String>{
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        userInfoService.deleteUser(userIdDTO.userId)
        return ResponseEntity.ok("정상적으로 삭제됨")
    }

    @Operation(summary = "유저 정보 확인", description = "token과 userId를 주면 유저의 정보 확인 API")
    @PostMapping("/info")
    fun userInfo(httpServletRequest: HttpServletRequest, @RequestBody userIdDTO: UserIdDTO) : ResponseEntity<UserInfoDTO>{
        val result =  jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }
        val userInfo = userInfoService.userInfo(userIdDTO.userId)

        return ResponseEntity.ok(userInfo)
    }

    @Operation(summary = "유저 정보 갱신", description = "token과 유저 정보를 주면 유저의 정보 갱신 API")
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