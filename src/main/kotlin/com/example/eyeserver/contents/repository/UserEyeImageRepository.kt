package com.example.eyeserver.contents.repository

import com.example.eyeserver.contents.domain.UserEyeImage
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface UserEyeImageRepository : MongoRepository< UserEyeImage,Long>{

    fun findByUserIdAndDateOrderByImageNum(userId : String, date : LocalDate) : List<UserEyeImage>
}