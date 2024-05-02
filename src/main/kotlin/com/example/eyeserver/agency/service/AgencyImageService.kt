package com.example.eyeserver.agency.service

import com.example.eyeserver.agency.domain.AgencyImage
import com.example.eyeserver.agency.dto.ResponseAgencyImageDTO
import com.example.eyeserver.agency.repository.AgencyImageRepository
import org.bson.BsonBinarySubType
import org.bson.types.Binary
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AgencyImageService (
    private val agencyImageRepository: AgencyImageRepository,
){



}