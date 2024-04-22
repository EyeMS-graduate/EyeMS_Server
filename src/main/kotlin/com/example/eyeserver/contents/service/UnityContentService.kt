package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.dto.RequestUserContentDTO
import com.example.eyeserver.contents.dto.ResponseUserContentDTO
import com.example.eyeserver.contents.repository.UserContentsRepository
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UnityContentService (
    val userContentsRepository: UserContentsRepository,
    val userRepository: UserRepository,
){
    fun saveUserContentScore(requestUserContentDTO: RequestUserContentDTO) : ResponseUserContentDTO{
        val user : Users = userRepository.findByUserId(requestUserContentDTO.userId)
        userContentsRepository.save(UserContents(
            userId = requestUserContentDTO.userId,
            contentName = requestUserContentDTO.contentName,
            date = LocalDate.now(),
            score = requestUserContentDTO.score,
            user = user,
            id = null
        ))
        return ResponseUserContentDTO(
            success = true,
            message = "good",
        )
    }


}