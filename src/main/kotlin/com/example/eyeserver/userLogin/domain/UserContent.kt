package com.example.eyeserver.userLogin.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class UserContent (
    @Id
    @Column(name = "user_id")
    val userId : String,


){
}