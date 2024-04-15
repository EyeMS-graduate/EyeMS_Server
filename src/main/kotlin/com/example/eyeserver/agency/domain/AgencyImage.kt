package com.example.eyeserver.agency.domain

import jakarta.persistence.Id
import org.bson.types.Binary
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "agencyImage")
class AgencyImage(
    @Id
    val agencyId : String,

    val firstImage : Binary,
    val secondImage : Binary,
    val thirdImage : Binary,
) {

}