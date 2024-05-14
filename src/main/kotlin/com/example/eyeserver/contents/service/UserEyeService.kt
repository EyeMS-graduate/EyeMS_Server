package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserEyeImage
import com.example.eyeserver.contents.domain.UserTest
import com.example.eyeserver.contents.dto.eyedto.RequestUserEyeImageDTO
import com.example.eyeserver.contents.dto.eyedto.ResponseUserEyeImageDTO
import com.example.eyeserver.contents.dto.unitydto.ResponseUserContentDTO
import com.example.eyeserver.contents.repository.UserEyeImageRepository
import com.example.eyeserver.contents.repository.UserTestRepository
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.stereotype.Service
import java.text.DecimalFormat
import java.time.LocalDate


@Service
class UserEyeService (
    private val userEyeImageRepository: UserEyeImageRepository,
    private val userTestRepository: UserTestRepository,
    private val userRepository: UserRepository,
){

    fun saveUserImage(requestUserEyeImageDTO: RequestUserEyeImageDTO) : ResponseUserContentDTO{
        val user: Users = userRepository.findByUserId(requestUserEyeImageDTO.userId)
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
        val df = DecimalFormat("#.#")
        val fixCount = df.format(requestUserEyeImageDTO.fixCount.stream().mapToDouble(Double::toDouble).average().asDouble).toDouble()
        val saccade = df.format(requestUserEyeImageDTO.saccade.stream().mapToDouble(Double::toDouble).average().asDouble).toDouble()
        val accurate = df.format(requestUserEyeImageDTO.accurate.stream().mapToDouble(Double::toDouble).average().asDouble).toDouble()
        val totalReadTime = df.format(requestUserEyeImageDTO.totalReadTime.stream().mapToDouble(Double::toDouble).average().asDouble).toDouble()
        val regression = df.format(requestUserEyeImageDTO.regression.stream().mapToDouble(Double::toDouble).average().asDouble).toDouble()
        val questionTime = df.format(requestUserEyeImageDTO.questionTime.stream().mapToDouble(Double::toDouble).average().asDouble).toDouble()


            userTestRepository.save(UserTest(
                userId = requestUserEyeImageDTO.userId,
                fixCount = fixCount,
                saccade = saccade,
                accurate = accurate,
                totalReadTime = totalReadTime,
                regression = regression,
                questionTime = questionTime,
                date = LocalDate.now(),
                user = user,
            ))

        return ResponseUserContentDTO(true, "good")
    }

    fun getUserAllImage(userId : String, date : String) : ResponseUserEyeImageDTO{
        val result = userEyeImageRepository.findByUserIdAndDateOrderByImageNum(userId, date)
        return ResponseUserEyeImageDTO(result[0], result[1], result[2])
    }
}