package com.example.eyeserver.agencyLogin.domain

import com.example.eyeserver.agencyLogin.role.Role
import com.example.eyeserver.userLogin.domain.Users
import jakarta.persistence.*



@Entity
@Table(name = "agency")

class Agency (

    @Id
    val userId : String,
    @Column(name = "password")
    val password : String,
    val name : String,

    @Column(name = "agency_name")
    val agencyName : String,
    val phone : String,

    @Column(name = "role")
    val role : Role,

    @OneToMany(mappedBy = "agency", cascade = [CascadeType.ALL])
    val users : List<Users> = ArrayList<Users>(),
)
{

}