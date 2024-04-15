package com.example.eyeserver.imsi.controller

import com.example.eyeserver.agency.repository.AgencyRepository
import com.example.eyeserver.imsi.DTO.UnityDataDTO
import com.example.eyeserver.imsi.UnityDataRepository
import com.example.eyeserver.imsi.domain.UnityData
import com.example.eyeserver.security.JwtTokenCheck
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class ImsiController (
    private val agencyRepository: AgencyRepository,
    private val unityDataRepository : UnityDataRepository,
    private val jwtTokenCheck: JwtTokenCheck,
){
    @PostMapping("/save")
    fun unitySave(@RequestBody unityDataDTO : UnityDataDTO) : String{


        val data = UnityData(
            userId = unityDataDTO.userId,
            image1 = unityDataDTO.image.substring(0, 500),
            image2 = unityDataDTO.image.substring(500,1000),
            image3 = unityDataDTO.image.substring(1000),
            date = unityDataDTO.date,
        )
        unityDataRepository.save(data)
        return "good"
    }

    @GetMapping("/look")
    fun hello(httpServletRequest: HttpServletRequest) : ResponseEntity<UnityDataDTO>{
        val result  = jwtTokenCheck.tokenCheck(httpServletRequest)
        if(result?.get(0) != "Manager"){
            return ResponseEntity.badRequest().body(null)
        }

        val firstUser = agencyRepository.findByAgencyId(result[1]).users[0].userId
        val unityData = unityDataRepository.findByUserId(firstUser)
        return ResponseEntity.ok(UnityDataDTO(
            userId = unityData.userId,
            date = unityData.date,
            image = unityData.image1+unityData.image2+unityData.image3,
        ))


    }
}