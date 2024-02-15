package com.example.eyeserver.agencyLogin.domain

import com.example.eyeserver.agencyLogin.role.Role
import jakarta.persistence.*



@Entity
@Table(name = "agency")
class Agency (

    @Id
    val userId : String,
    @Column(name = "password")
    val password : String,

    @Column(name = "agency_name")
    val agencyName : String,


    @Column(name = "role")
    val role : Role,

    )
{

}