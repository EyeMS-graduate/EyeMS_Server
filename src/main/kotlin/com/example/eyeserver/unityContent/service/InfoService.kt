package com.example.eyeserver.unityContent.service

import com.example.eyeserver.unityContent.dto.InfoRequestDTO
import com.example.eyeserver.unityContent.dto.InfoDTO
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class InfoService(val userRepository: UserRepository ) {

    fun sendInfo(infoRequestDTO: InfoRequestDTO): InfoDTO? {
        val userId = infoRequestDTO.userId
        val user: Users? = userRepository.findByUserId(userId)

        if (user === null) return null

        val info = InfoDTO(
            userId = userId,
            name = user.name,
            birth = user.birth,
            phone = user.phone,
            email = user.email,
            address = user.address,
            gender = user.gender,
            glasses = user.glasses
        )
        return info
    }

    fun receiveInfo(infoDTO: InfoDTO): Boolean {
        val userId = infoDTO.userId
        val user: Users? = userRepository.findByUserId(userId)

        if (user === null) return false

        user.name = infoDTO.name
        user.birth = infoDTO.birth
        user.phone = infoDTO.phone
        user.email = infoDTO.email
        user.address = infoDTO.address
        user.gender = infoDTO.gender
        user.glasses = infoDTO.glasses

        userRepository.save(user)

        return true
    }

}