package com.example.eyeserver.agencyLogin.domain

import com.example.eyeserver.agencyLogin.role.Role
import com.example.eyeserver.userLogin.domain.Users
import jakarta.persistence.*



@Entity
@Table(name = "agency")

class Agency (

    @Id
    @Column(name = "agency_id")
    val agencyId : String,
    val password : String,
    val name : String,

    @Column(name = "agency_name")
    val agencyName : String,
    val phone : String,
    val role : Role,

    @OneToMany(mappedBy = "agency", cascade = [CascadeType.ALL])
    val users : List<Users> = ArrayList<Users>(),
)
{

}