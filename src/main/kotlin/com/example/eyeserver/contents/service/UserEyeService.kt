package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserEyeImage
import com.example.eyeserver.contents.domain.UserTest
import com.example.eyeserver.contents.dto.eyedto.RequestUserEyeImageDTO
import com.example.eyeserver.contents.dto.eyedto.ResponseUserEyeImageDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.repository.UserEyeImageRepository
import com.example.eyeserver.contents.repository.UserTestRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue

@Service
class UserEyeService (
    private val userEyeImageRepository: UserEyeImageRepository,
    private val userTestRepository: UserTestRepository,
){

    fun saveUserImage(requestUserEyeImageDTO: RequestUserEyeImageDTO) : ResponseUserContentDTO{
        for (i in 0..2){
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
                date = LocalDate.now().toString(),
            ))
        }
            userTestRepository.save(UserTest(
                userId = requestUserEyeImageDTO.userId,
                fixCount = requestUserEyeImageDTO.fixCount.stream().mapToDouble(Double::toDouble).average().asDouble,
                saccade = requestUserEyeImageDTO.saccade.stream().mapToDouble(Double::toDouble).average().asDouble,
                accurate = requestUserEyeImageDTO.saccade.stream().mapToDouble(Double::toDouble).average().asDouble,
                totalReadTime = requestUserEyeImageDTO.saccade.stream().mapToDouble(Double::toDouble).average().asDouble,
                regression = requestUserEyeImageDTO.saccade.stream().mapToDouble(Double::toDouble).average().asDouble,
                questionTime = requestUserEyeImageDTO.saccade.stream().mapToDouble(Double::toDouble).average().asDouble,
                date = LocalDate.now(),
            ))

        return ResponseUserContentDTO(true, "good")
    }

    fun getUserAllImage(userId : String, date : String) : ResponseUserEyeImageDTO{
        val result = userEyeImageRepository.findByUserIdAndDateOrderByImageNum(userId, date)
        return ResponseUserEyeImageDTO(result[0], result[1], result[2])
    }
}