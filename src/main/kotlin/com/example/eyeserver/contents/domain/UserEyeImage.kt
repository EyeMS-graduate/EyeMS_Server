package com.example.eyeserver.contents.domain

import jakarta.persistence.Id
import org.bson.types.Binary
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.Date

@Document(collection = "eyeImage")
class UserEyeImage (
    @Id
    val userId : String,

    val image : Binary,
    val imageNum : Int,

    val fixCount : Double,
    val saccade : Double,
    val totalReadTime : Double,
    val accurate : Double,
    val regression : Double,
    val questionTime : Double,

    val dyslexiaScore : Double,
    val date : LocalDate,
    )