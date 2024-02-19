package com.example.eyeserver.userLogin.domain

import com.example.eyeserver.agencyLogin.domain.Agency
import com.example.eyeserver.agencyLogin.role.Role
import jakarta.persistence.*

@Entity
class Users(

    @Id
    @Column(name = "user_id")
    val userId: String,

    var password: String,
    var status : String?,
    var name: String,
    var agencyName : String,
    var birth: String,
    var phone: String,
    var email: String,
    var address: String,
    var gender: Boolean,
    var firstVisit: Boolean,
    var glasses: Boolean,
    var role : Role,

    @ManyToOne
    @JoinColumn(name = "agency")
    var agency: Agency? = null

){

}