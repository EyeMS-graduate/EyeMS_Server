package com.example.eyeserver.agency.service

import com.example.eyeserver.security.JwtTokenProvider

import com.example.eyeserver.agency.domain.Agency
import com.example.eyeserver.agency.dto.*
import com.example.eyeserver.agency.repository.AgencyRepository


import com.example.eyeserver.agency.role.Role
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class AgencyService(
    @Autowired val userRepository: AgencyRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired private val jwtTokenProvider: JwtTokenProvider,
){
    var roomCount : Int = 1
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
        val year = agencyDTO.birth.substring(0,4)
        val month = agencyDTO.birth.substring(4,6)
        val day = agencyDTO.birth.substring(6)

        val firstCall = agencyDTO.phone.substring(0,3)
        val secondCall = agencyDTO.phone.substring(3,7)
        val thirdCall = agencyDTO.phone.substring(7)


        val fixBirth = "$year-$month-$day"
        val fixPhone = "$firstCall-$secondCall-$thirdCall"
        val user = Agency(
            agencyId =  agencyDTO.agencyId,
            password =  passwordEncoder.encode(agencyDTO.password),
            name =  agencyDTO.name,
            agencyName =  agencyDTO.agencyName,
            phone =  fixPhone,
            role = Role.Manager,
            room = roomCount++,
            birth = fixBirth,
            address = agencyDTO.address,
            gender = agencyDTO.gender,
            email = agencyDTO.email,
            date = LocalDate.now(),
            )

        userRepository.save(user)

        return AgencySignUpDTO(
            agencyId = user.agencyId,
            password = user.password,
            name = user.name,
            agencyName = user.name,
            phone = user.phone,
            birth = user.birth,
            address = user.address,
            gender = user.gender,
            email = user.email,
        )
    }


    fun agencyInfo(agencyId : String) : ResponseAgencyInfoDTO{
        val agency = userRepository.findByAgencyId(agencyId)
        return ResponseAgencyInfoDTO(
            agencyId = agency.agencyId,
            name = agency.name,
            agencyName = agency.agencyName,
            phone = agency.phone,
            address = agency.address,
            birth = agency.birth,
            gender = agency.gender,
            email = agency.email,
            date = agency.date,
        )
    }

    @Transactional
    fun updateAgencyInfo(agencyInfoDTO: AgencyInfoDTO) : Boolean{
        if(!userRepository.existsByAgencyId(agencyInfoDTO.agencyId)){
            return false
        }
        val agency = userRepository.findByAgencyId(agencyInfoDTO.agencyId)
        agency.phone = agencyInfoDTO.phone
        agency.password = passwordEncoder.encode(agencyInfoDTO.password)
        agency.email = agencyInfoDTO.email
        agency.address = agencyInfoDTO.address
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