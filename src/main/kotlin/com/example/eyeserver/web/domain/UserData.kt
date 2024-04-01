package com.example.eyeserver.web.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.Date

@Entity
class UserData (
    @Id
    @Column(name = "user_id")
    val userId: String,

    val improveContent1 : String,
    val improveContent2 : String,
    val improveContent3 : String,
    val date : Date,

    val dyslexia : Boolean,
    val inspectionDataX : String,
    val inspectionDataY : String,
    val inspectionDataWord : String,

){

}