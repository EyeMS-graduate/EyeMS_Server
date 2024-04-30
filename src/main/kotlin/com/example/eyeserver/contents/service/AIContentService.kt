package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserTest
import com.example.eyeserver.contents.dto.webdto.RequestUserTestDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.repository.UserTestRepository
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AIContentService (
    val userTestRepository: UserTestRepository,
    val userRepository: UserRepository,
){

    fun saveTestContent(requestUserTestDTO: RequestUserTestDTO) : ResponseUserContentDTO {
        val user : Users = userRepository.findByUserId(requestUserTestDTO.userId)
        userTestRepository.save(UserTest(
            id = null,
            userId = requestUserTestDTO.userId,
            fixCount = requestUserTestDTO.fixCount,
            saccade = requestUserTestDTO.saccade,
            totalReadTime = requestUserTestDTO.totalReadTime,
            accurate = requestUserTestDTO.accurate,
            regression = requestUserTestDTO.regression,
            questionTime = requestUserTestDTO.questionTime,
            date = LocalDate.now(),
            user = user,
        ))
        return ResponseUserContentDTO(
            success = true,
            message = "good"
        )
    }
}