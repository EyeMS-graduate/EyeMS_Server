package com.example.eyeserver.agencyLogin.service

import com.example.eyeserver.security.JwtTokenProvider

import com.example.eyeserver.agencyLogin.domain.Agency
import com.example.eyeserver.agencyLogin.dto.*
import com.example.eyeserver.agencyLogin.repository.AgencyRepository


import com.example.eyeserver.agencyLogin.role.Role
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AgencyService(
    @Autowired val userRepository: AgencyRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtTokenProvider: JwtTokenProvider,
){
    var roomCount : Int = 0
    fun signIn(agencySignInDTO : AgencySignInDTO) : TokenAndRoomResponseDTO{

        val user = userRepository.findByAgencyId(agencySignInDTO.agencyId)

        if(!passwordEncoder.matches(agencySignInDTO.password, user.password)){
            print("비밀번호 불일치")

            return TokenAndRoomResponseDTO(
                token = "",
                utcExpirationDate = Date(),
                room = "",
            )
        }

        val jwtInfo = jwtTokenProvider.createToken(user.agencyId, user.role, user.agencyName)

        print(jwtTokenProvider.userPrimaryKey(jwtToken = jwtInfo.token))

        return TokenAndRoomResponseDTO(
            token = jwtInfo.token,
            utcExpirationDate = jwtInfo.utcExpirationDate,
            room = user.room.toString(),
        )

    }

    fun signUp(agencyDTO: AgencySignUpDTO) : AgencySignUpDTO{
        val user = Agency(agencyDTO.agencyId, passwordEncoder.encode(agencyDTO.password), agencyDTO.name, agencyDTO.agencyName, agencyDTO.phone, Role.Manager, roomCount++)

        userRepository.save(user)

        return AgencySignUpDTO(user.agencyId, passwordEncoder.encode(user.password), user.name, user.agencyName, user.phone)
    }


    fun agencyInfo(agencyId : String) : ResponseAgencyInfoDTO{
        val agency = userRepository.findByAgencyId(agencyId)
        return ResponseAgencyInfoDTO(
            agencyId = agency.agencyId,
            name = agency.name,
            agencyName = agency.agencyName,
            phone = agency.phone
        )
    }

    @Transactional
    fun updateAgencyInfo(agencyInfoDTO: AgencyInfoDTO) : Boolean{
        if(!userRepository.existsByAgencyId(agencyInfoDTO.agencyId)){
            return false
        }
        val agency = userRepository.findByAgencyId(agencyInfoDTO.agencyId)
        agency.agencyName = agencyInfoDTO.agencyName
        agency.agencyId = agencyInfoDTO.agencyId
        agency.name = agencyInfoDTO.name
        agency.phone = agencyInfoDTO.phone
        agency.password = passwordEncoder.encode(agencyInfoDTO.password)
        userRepository.save(agency)
        return true
    }

    @Transactional
    fun deleteAgencyInfo(agencyId: String) : Boolean{
        return try {
            userRepository.deleteById(agencyId)
            true
        } catch (e : Exception){
            false
        }

    }

    fun roomInfo(agencyId: String) : RoomDTO{
        return RoomDTO(room = userRepository.findByAgencyId(agencyId).room.toString())
    }

}