package com.example.eyeserver.contents.dto.eyedto

import com.example.eyeserver.contents.domain.UserEyeImage
import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.Binary

@Schema(description = "유저 test 결과 이미지 3개 DTO 제공")
class ResponseUserEyeImageDTO (
    val firstImage : UserEyeImage,
    val secondImage : UserEyeImage,
    val thirdImage : UserEyeImage,
)