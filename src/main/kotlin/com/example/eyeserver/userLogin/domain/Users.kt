package com.example.eyeserver.userLogin.domain

import com.example.eyeserver.agencyLogin.domain.Agency
import com.example.eyeserver.agencyLogin.role.Role
import jakarta.persistence.*

@Entity
data class Users (

    @Id
    @Column(name = "user_id")
    val userId: String,

    var password: String,
    var status : String?,
    var name: String,

    @Column(name = "agency_name")
    var agencyName : String,
    var birth: String,
    var phone: String,
    var email: String,
    var address: String,
    var gender: Boolean,
    var visited: Boolean,
    var glasses: Boolean,

    @ManyToOne
    @JoinColumn(name = "agency")
    var agency: Agency,

)