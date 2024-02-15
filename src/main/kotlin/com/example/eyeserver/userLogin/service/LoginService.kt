package com.example.eyeserver.userLogin.service

import com.example.eyeserver.Security.JwtTokenProvider
import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO
import com.example.eyeserver.userLogin.dto.LoginDTO
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.LoginRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class LoginService (
    val loginRepository: LoginRepository,
    private val passwordEncoder: PasswordEncoder,
    val jwtTokenProvider: JwtTokenProvider
){



}