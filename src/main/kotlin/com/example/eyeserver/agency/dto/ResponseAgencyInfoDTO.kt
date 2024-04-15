package com.example.eyeserver.agency.dto

import java.time.LocalDate
import java.util.Date

data class ResponseAgencyInfoDTO (
    val agencyId : String,
    val name : String,
    val agencyName : String,
    val phone : String,
    val address : String,
    val birth : String,
    val gender : String,
    val email : String,
    val date : LocalDate,
)