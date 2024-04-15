package com.example.eyeserver.userLogin.service

import com.example.eyeserver.agency.role.Role
import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.dto.LoginRequestDTO
import com.example.eyeserver.userLogin.dto.LoginResponseDTO
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginService (
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    val jwtTokenProvider: JwtTokenProvider
){

    @Transactional
    fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO? {
        val id: String = loginRequestDTO.userId
        val pw: String = loginRequestDTO.password

        var users: Users? = userRepository.findByUserId(id)

        if(users === null || !passwordEncoder.matches(pw, users.password)){
            return null
        }

        val visited: Boolean = users.visited

        if (visited === false){
            users.visited = true
            userRepository.save(users)
        }

        val jwtInfo = jwtTokenProvider.createToken(users.userId, Role.User, users.agencyName)
        return LoginResponseDTO(jwtInfo.token, visited)
    }

}