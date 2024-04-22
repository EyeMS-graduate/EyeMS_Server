package com.example.eyeserver.contents.repository

import com.example.eyeserver.contents.domain.UserEyeImage
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEyeImageRepository : MongoRepository< UserEyeImage,Long>{

}