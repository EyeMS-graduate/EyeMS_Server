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


    fun addImages(files : MultipartFile): String {
        val agencyImage = AgencyImage(
            agencyId = "1",
            firstImage = Binary(BsonBinarySubType.BINARY, files.bytes),
            secondImage = Binary(BsonBinarySubType.BINARY, files.bytes),
            thirdImage = Binary(BsonBinarySubType.BINARY, files.bytes)
        )
        val fileId =  agencyImageRepository.insert(agencyImage)
        return fileId.toString()
    }

    fun getImage(id : String): Binary {
        val agency : List<AgencyImage> = agencyImageRepository.findByAgencyId(id)
        return agency[0].firstImage
    }




}