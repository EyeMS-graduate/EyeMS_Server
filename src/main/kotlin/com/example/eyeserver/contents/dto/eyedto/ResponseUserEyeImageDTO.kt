package com.example.eyeserver.contents.dto.eyedto

import com.example.eyeserver.contents.domain.UserEyeImage
import org.bson.types.Binary

class ResponseUserEyeImageDTO (
    val firstImage : UserEyeImage,
    val secondImage : UserEyeImage,
    val thirdImage : UserEyeImage,
)