package com.example.eyeserver.agencyLogin.dto

import com.example.eyeserver.agencyLogin.role.Role


class AgencyDTO (
    val userId : String,
    val password : String,
    val agency : String,
    val role : Role,
    )