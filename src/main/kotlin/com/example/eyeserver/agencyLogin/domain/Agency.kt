package com.example.eyeserver.agencyLogin.domain

import jakarta.persistence.*



@Entity
@Table(name = "agency")
class Agency (

    @Id
    val userId : String,
    @Column(name = "password")
    val password : String,


)
{

}