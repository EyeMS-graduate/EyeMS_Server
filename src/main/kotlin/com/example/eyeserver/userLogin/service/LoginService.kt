package com.example.eyeserver.userLogin.service

import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO
import com.example.eyeserver.agencyLogin.role.Role
import com.example.eyeserver.security.JwtTokenProvider
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.dto.LoginDTO
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class LoginService (
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    val jwtTokenProvider: JwtTokenProvider
){

    @Transactional
    fun login(loginDTO: LoginDTO): TokenResponseDTO? {
        val id: String = loginDTO.id
        val pw: String = loginDTO.pw

        val users: Users? = userRepository.findByUserId(id)


        if(users === null || !passwordEncoder.matches(pw, users.password)){
            return TokenResponseDTO(
                token = "",
                utcExpirationDate = Date(),
            )
        }

        val jwtInfo = jwtTokenProvider.createToken(users.userId, Role.User, users.agencyName)
        return TokenResponseDTO(
            token = jwtInfo.token,
            utcExpirationDate = jwtInfo.utcExpirationDate,
        )
    }

}