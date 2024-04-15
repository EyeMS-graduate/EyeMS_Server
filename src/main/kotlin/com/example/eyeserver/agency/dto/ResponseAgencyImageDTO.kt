package com.example.eyeserver.agency.dto

import org.bson.types.Binary

class ResponseAgencyImageDTO(
    val firstImage : Binary,
    val secondImage : Binary,
    val thirdImage : Binary,
) {
}