package com.example.eyeserver.imsi.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Entity
@Table(name = "unityData")
class UnityData (
    @Id
    val userId : String,
    @Column
    val date : String,

    @Column(columnDefinition = "LONGTEXT")
    val image1 : String,
    @Column(columnDefinition = "LONGTEXT")
    val image2 : String,
    @Column(columnDefinition = "LONGTEXT")
    val image3 : String,
)