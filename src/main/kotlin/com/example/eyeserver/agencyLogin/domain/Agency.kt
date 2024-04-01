package com.example.eyeserver.agencyLogin.domain

import com.example.eyeserver.agencyLogin.role.Role
import com.example.eyeserver.userLogin.domain.Users
import jakarta.persistence.*



@Entity
@Table(name = "agency")

class Agency (

    @Id
    @Column(name = "agency_id")
    var agencyId : String,
    var password : String,
    var name : String,

    @Column(name = "agency_name")
    var agencyName : String,
    var phone : String,
    val role : Role,

    @Column(name = "room_id")
    val room : Int,
    @OneToMany(mappedBy = "agency", cascade = [CascadeType.ALL])
    val users : List<Users> = ArrayList<Users>(),
)
{

}