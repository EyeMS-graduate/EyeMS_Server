package com.example.eyeserver.agency.repository

import com.example.eyeserver.agency.domain.AgencyImage
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyImageRepository : MongoRepository<AgencyImage, String>{

    fun findByAgencyIdAndImageNum(agencyId : String, imageNum : Int) : List<AgencyImage>

}