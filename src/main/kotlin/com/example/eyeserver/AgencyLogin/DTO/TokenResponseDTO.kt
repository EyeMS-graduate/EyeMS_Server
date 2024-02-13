package com.example.eyeserver.AgencyLogin.DTO


import com.example.eyeserver.AgencyLogin.Role.Role
import java.util.*

class TokenResponseDTO (
    var token : String,
    var utcExpirationDate : Date,
)