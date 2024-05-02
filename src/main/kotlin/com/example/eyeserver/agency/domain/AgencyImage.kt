package com.example.eyeserver.agency.domain

import jakarta.persistence.Id
import org.bson.types.Binary
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "agencyImage")
class AgencyImage(
    @Id
    val agencyId : String,

    val image : Binary,
    val imageNum : Int,
    val fixCount : Double,
    val saccade : Double,
    val totalReadTime : Double,
    val accurate : Double,
    val regression : Double,
    val questionTime : Double,
    val date : LocalDate,

) {

}