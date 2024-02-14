package com.example.eyeserver.userLogin.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Users (

    @Id
    @Column(name = "user_id")
    val userId: String,

    var password: String,
    var agency: String,
    var status: String,
    var name: String,
    var birth: String,
    var gender: String,
    var phone: String,
    var glasses: String,

)