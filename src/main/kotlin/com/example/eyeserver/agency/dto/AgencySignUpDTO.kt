package com.example.eyeserver.agency.dto

import jakarta.persistence.Column

data class AgencySignUpDTO (
    val agencyId : String,
    val password : String,
    val agencyName : String,
    val name : String,
    val phone : String,
    val birth : String,
    val address : String,
    val gender : String,
    val email : String,
)