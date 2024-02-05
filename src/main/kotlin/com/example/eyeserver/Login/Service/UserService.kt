package com.example.eyeserver.Login.Service

import com.example.eyeserver.Login.DTO.UserDTO
import com.example.eyeserver.Login.Domain.User
import com.example.eyeserver.Login.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService (
    @Autowired val userRepository : UserRepository
){
    fun signIn(userDTO: UserDTO){
    }

    fun signUp(userDTO: UserDTO) : UserDTO{
        val user = User(userDTO.userId, userDTO.password)
        userRepository.save(user)

        return UserDTO(user.userId, user.password)
    }

}