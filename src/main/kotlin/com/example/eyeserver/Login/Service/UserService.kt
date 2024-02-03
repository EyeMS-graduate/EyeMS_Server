package com.example.eyeserver.Login.Service

import com.example.eyeserver.Login.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService (
    @Autowired
    val userRepository : UserRepository
){
    fun testing(){
        userRepository.findByUserId("1")
    }

}