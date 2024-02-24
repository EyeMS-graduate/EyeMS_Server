package com.example.eyeserver.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class JwtTokenCheck (
    private val jwtTokenProvider: JwtTokenProvider,
){

    fun tokenCheck(httpServletRequest: HttpServletRequest) : MutableList<String>?{
        val token = jwtTokenProvider.resolveToken(httpServletRequest)

        if(token == null || !token.startsWith("Bearer ")){
            return null
        }

        val jwtToken = token.split(" ")[1].trim()

        if(!jwtTokenProvider.validateToken(jwtToken)){
            return null
        }

        val role = jwtTokenProvider.userPrimaryKey(jwtToken).subject
        val agencyId = jwtTokenProvider.userPrimaryKey(jwtToken)["userId"].toString()
        val agency = jwtTokenProvider.userPrimaryKey(jwtToken)["agency"].toString()

        val result : MutableList<String> = mutableListOf()
        result.add(role)
        result.add(agencyId)
        result.add(agency)
        return result
    }
}