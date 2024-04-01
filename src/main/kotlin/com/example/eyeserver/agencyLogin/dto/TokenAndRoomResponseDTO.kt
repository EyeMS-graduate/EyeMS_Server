package com.example.eyeserver.agencyLogin.dto

import java.util.*

class TokenAndRoomResponseDTO (
    var token : String,
    var utcExpirationDate : Date,
    var room : String,
){

}