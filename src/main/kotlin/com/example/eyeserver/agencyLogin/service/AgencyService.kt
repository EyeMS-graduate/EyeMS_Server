package com.example.eyeserver.agencyLogin.service

import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO

import com.example.eyeserver.agencyLogin.domain.Agency
import com.example.eyeserver.agencyLogin.dto.AgencySignInDTO
import com.example.eyeserver.agencyLogin.dto.AgencySignUpDTO
import com.example.eyeserver.agencyLogin.repository.AgencyRepository


import com.example.eyeserver.agencyLogin.role.Role
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
    fun signIn(agencySignInDTO : AgencySignInDTO) : TokenResponseDTO{

        val user = userRepository.findByAgencyId(agencySignInDTO.agencyId)

        if(!passwordEncoder.matches(agencySignInDTO.password, user.password)){
            print("비밀번호 불일치")

            return TokenResponseDTO(
                token = "",
                utcExpirationDate = Date(),
            )
        }

        val jwtInfo = jwtTokenProvider.createToken(user.agencyId, user.role, user.agencyName)

        print(jwtTokenProvider.userPrimaryKey(jwtToken = jwtInfo.token))

        return TokenResponseDTO(
            token = jwtInfo.token,
            utcExpirationDate = jwtInfo.utcExpirationDate,
        )
    }

    fun signUp(agencyDTO: AgencySignUpDTO) : AgencySignUpDTO{
        val user = Agency(agencyDTO.agencyId, passwordEncoder.encode(agencyDTO.password), agencyDTO.name, agencyDTO.agencyName, agencyDTO.phone, Role.Manager)
        userRepository.save(user)

        return AgencySignUpDTO(user.agencyId, passwordEncoder.encode(user.password), user.name, user.agencyName, user.phone)
    }

}