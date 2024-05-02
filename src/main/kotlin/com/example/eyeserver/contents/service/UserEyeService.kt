package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserEyeImage
import com.example.eyeserver.contents.dto.eyedto.RequestUserEyeImageDTO
import com.example.eyeserver.contents.dto.eyedto.ResponseUserEyeImageDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.repository.UserEyeImageRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserEyeService (
    private val userEyeImageRepository: UserEyeImageRepository,
){

    fun saveUserImage(requestUserEyeImageDTO: RequestUserEyeImageDTO) : ResponseUserContentDTO{
        for (i in 0..3){
            userEyeImageRepository.insert(UserEyeImage(
                userId = requestUserEyeImageDTO.userId,
                image = requestUserEyeImageDTO.image[i],
                imageNum = i,
                fixCount = requestUserEyeImageDTO.fixCount[i],
                saccade = requestUserEyeImageDTO.saccade[i],
                totalReadTime = requestUserEyeImageDTO.totalReadTime[i],
                accurate = requestUserEyeImageDTO.accurate[i],
                regression = requestUserEyeImageDTO.regression[i],
                questionTime = requestUserEyeImageDTO.questionTime[i],
                dyslexiaScore = requestUserEyeImageDTO.dyslexiaScore[i],
                date = LocalDate.now(),
            ))
        }
        return ResponseUserContentDTO(true, "good")
    }

    fun getUserAllImage(userId : String, date : LocalDate) : ResponseUserEyeImageDTO{
        val result = userEyeImageRepository.findByUserIdAndDateOrderByImageNum(userId, date)
        return ResponseUserEyeImageDTO(result[0].image, result[1].image, result[2].image)
    }
}