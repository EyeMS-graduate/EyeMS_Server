package com.example.eyeserver

import com.example.eyeserver.AgencyLogin.DTO.AgencyDTO
import com.example.eyeserver.AgencyLogin.Repository.AgencyRepository
import com.example.eyeserver.AgencyLogin.Role.Role
import com.example.eyeserver.AgencyLogin.Service.AgencyService
import org.junit.jupiter.api.Assertions.assertNotEquals

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SignServiceTest(
    @Autowired
    val userService: AgencyService,

    @Autowired
    val userRepository: AgencyRepository
) {

    @Test
    fun passwordTesting() {

        //given
        val userDTO = AgencyDTO(
            userId = "123",
            password = "3q4mf9ao8eirghj",

        )

        //when
        userService.signUp(userDTO)

        //then
        val findSignUpUser = userRepository.existsByUserId(userDTO.userId)

        if (findSignUpUser) {

        }
    }
}