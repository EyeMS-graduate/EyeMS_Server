package com.example.eyeserver.agencyLogin.controller

import com.example.eyeserver.agencyLogin.dto.AgencyInfoDTO
import com.example.eyeserver.agencyLogin.dto.ResponseAgencyInfoDTO
import com.example.eyeserver.agencyLogin.dto.RoomDTO
import com.example.eyeserver.agencyLogin.service.AgencyService
import com.example.eyeserver.security.JwtTokenCheck
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/agency")
class AgencyInfoController (
    private val jwtTokenCheck: JwtTokenCheck,
    private val agencyService: AgencyService
){
    @GetMapping("/test")
    fun test() : String{
        return "hello"
    }
    @GetMapping("/info")
    fun agencyInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<ResponseAgencyInfoDTO>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok(agencyService.agencyInfo(result[1]))
    }


    @PostMapping("/update")
    fun updateAgencyInfo(httpServletRequest: HttpServletRequest, @RequestBody agencyInfoDTO: AgencyInfoDTO) : ResponseEntity<String>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body("매니저가 아닙니다")
        }

        if(!agencyService.updateAgencyInfo(agencyInfoDTO)){
            return ResponseEntity.badRequest().body("정보 갱신 실패")
        }

        return ResponseEntity.ok("수정 완료")
    }

    @PostMapping("/delete")
    fun deleteAgencyInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<Boolean> {
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(false)
        }

        return ResponseEntity.ok(agencyService.deleteAgencyInfo(result[1]))
    }

    @GetMapping("/room")
    fun roomInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<RoomDTO>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok(agencyService.roomInfo(result[1]))
    }
}