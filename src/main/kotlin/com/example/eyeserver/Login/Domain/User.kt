package com.example.eyeserver.Login.Domain

import jakarta.persistence.*
import lombok.Data


@Entity
@Table(name = "user")
class User (
    @Id
    val userId : String,
    @Column(name = "password")
    val password : String,

)