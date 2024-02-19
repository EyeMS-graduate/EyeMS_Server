package com.example.eyeserver

import com.example.eyeserver.agencyLogin.dto.AgencyDTO
import com.example.eyeserver.agencyLogin.repository.AgencyRepository
import com.example.eyeserver.agencyLogin.service.AgencyService

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


}