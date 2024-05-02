package com.example.eyeserver.contents.dto.eyedto

import org.bson.types.Binary

class ResponseUserEyeImageDTO (
    val firstImage : Binary,
    val secondImage : Binary,
    val thirdImage : Binary,
)