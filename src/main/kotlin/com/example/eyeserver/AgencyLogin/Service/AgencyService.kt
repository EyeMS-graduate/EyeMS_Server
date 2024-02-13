package com.example.eyeserver.AgencyLogin.Service

import com.example.eyeserver.AgencyLogin.DTO.TokenResponseDTO
import com.example.eyeserver.AgencyLogin.DTO.AgencyDTO
import com.example.eyeserver.AgencyLogin.Domain.Agency
import com.example.eyeserver.AgencyLogin.Repository.AgencyRepository
import com.example.eyeserver.Security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AgencyService(
    @Autowired val userRepository: AgencyRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtTokenProvider: JwtTokenProvider
){
    fun signIn(agencyDTO: AgencyDTO) : TokenResponseDTO{

        val user = userRepository.findByUserId(agencyDTO.userId)
        if(!passwordEncoder.matches(agencyDTO.password, user.password)){
            print("비밀번호 불일치")

            return TokenResponseDTO(
                token = "",
                utcExpirationDate = Date(),
            )
        }
        val jwtInfo = jwtTokenProvider.createToken(user.userId)
        return TokenResponseDTO(
            token = jwtInfo.token,
            utcExpirationDate = jwtInfo.utcExpirationDate,

        )
    }

    fun signUp(agencyDTO: AgencyDTO) : AgencyDTO{
        val user = Agency(agencyDTO.userId, passwordEncoder.encode(agencyDTO.password))
        userRepository.save(user)

        return AgencyDTO(user.userId, passwordEncoder.encode(user.password))
    }

}