package com.example.eyeserver.agency.controller

import com.example.eyeserver.agency.dto.RequestAgencyInfoDTO
import com.example.eyeserver.agency.dto.ResponseAgencyInfoDTO
import com.example.eyeserver.agency.dto.RoomDTO
import com.example.eyeserver.agency.service.AgencyService
import com.example.eyeserver.security.JwtTokenCheck
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자 상태 API", description = "관리자의 정보 및 수정 삭제를 담당한다.")
@RestController
@RequestMapping("/agency")
class AgencyInfoController (
    private val jwtTokenCheck: JwtTokenCheck,
    private val agencyService: AgencyService
){
    @Operation(summary = "관리자 정보 확인", description = "관리자의 모든 정보를 확인 가능")
    @GetMapping("/info")
    fun agencyInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<ResponseAgencyInfoDTO>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok(agencyService.agencyInfo(result[1]))
    }


    @Operation(summary = "관리자 정보 업데이트", description = "토큰과 관련 정보를 넘겨줄 시 관리자 정보 갱신")
    @PostMapping("/update")
    fun updateAgencyInfo(httpServletRequest: HttpServletRequest, @RequestBody requestAgencyInfoDTO: RequestAgencyInfoDTO) : ResponseEntity<String>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body("매니저가 아닙니다")
        }

        if(!agencyService.updateAgencyInfo(requestAgencyInfoDTO)){
            return ResponseEntity.badRequest().body("정보 갱신 실패")
        }

        return ResponseEntity.ok("수정 완료")
    }
    @Operation(summary = "관리자 데이터 삭제", description = "토큰을 주면 삭제")
    @PostMapping("/delete")
    fun deleteAgencyInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<Boolean> {
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(false)
        }

        return ResponseEntity.ok(agencyService.deleteAgencyInfo(result[1]))
    }

    @Operation(summary = "관리자 방 번호 확인", description = "토근을 줄 시 관리자의 지정 된 방 번호 확인")
    @GetMapping("/room")
    fun roomInfo(httpServletRequest: HttpServletRequest) : ResponseEntity<RoomDTO>{
        val result = jwtTokenCheck.tokenCheck(httpServletRequest)

        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        return ResponseEntity.ok(agencyService.roomInfo(result[1]))
    }
}