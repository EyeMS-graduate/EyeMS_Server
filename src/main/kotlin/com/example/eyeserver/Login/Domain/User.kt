package com.example.eyeserver.Login.Domain

import jakarta.persistence.*


@Entity
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId : String,
    @Column(name = "password")
    var password : String,

)