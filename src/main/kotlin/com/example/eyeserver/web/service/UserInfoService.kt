package com.example.eyeserver.web.service

import com.example.eyeserver.agencyLogin.repository.AgencyRepository
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.UserRepository
import com.example.eyeserver.web.dto.UserInfoDTO
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserInfoService (
    private val agencyRepository: AgencyRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,

){
    fun userList(agencyId : String) : MutableList<String>{
        val users = agencyRepository.findByAgencyId(agencyId).users
        var usersId : MutableList<String> = mutableListOf()
        for (u in users){
            usersId.add(u.userId)
        }
        return usersId
    }

    @Transactional
    fun deleteUser(userId : String) {
        userRepository.deleteByUserId(userId)

    }

    fun userInfo(userId: String) : UserInfoDTO{
        val userInfo : Users = userRepository.findByUserId(userId)
        var gender : String? = "female"
        var glasses : String? = "no"
        if(userInfo.gender){
            gender = "male"
        }
        if(userInfo.glasses){
            glasses = "ok"
        }
        return UserInfoDTO(
            userId = userInfo.userId,
            password = "",
            name = userInfo.name,
            birth = userInfo.birth,
            gender = gender,
            email = userInfo.email,
            glasses = glasses,
            phone = userInfo.phone,
            address = userInfo.address
        )
    }

    @Transactional
    fun updateUserInfo(userInfoDTO: UserInfoDTO) : Boolean{
        if(!userRepository.existsByUserId(userInfoDTO.userId)){
            return false
        }
        val userInfo = userRepository.findByUserId(userInfoDTO.userId)
        var gender = false
        var glasses  = false
        if(userInfoDTO.gender == "male"){
            gender = true
        }
        if(userInfoDTO.glasses == "ok"){
            glasses = true
        }
        userInfo.password = passwordEncoder.encode(userInfoDTO.password)
        userInfo.name = userInfoDTO.name
        userInfo.birth = userInfoDTO.birth.substring(0,10)
        userInfo.gender = gender
        userInfo.email = userInfoDTO.email
        userInfo.glasses = glasses
        userInfo.phone = userInfoDTO.phone
        userInfo.address = userInfoDTO.address
        userRepository.save(userInfo)
        return true
    }


}