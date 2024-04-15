package com.example.eyeserver.agency.domain

import com.example.eyeserver.agency.role.Role
import com.example.eyeserver.userLogin.domain.Users
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


@Entity
@Table(name = "agency")

class Agency(

    @Id
    @Column(name = "agency_id")
    var agencyId: String,
    var password: String,
    var name: String,

    @Column(name = "agency_name")
    var agencyName: String,
    var phone: String,
    val role: Role,

    @Column(name = "room_id")
    val room: Int,

    @Column(name = "birth")
    val birth: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "gender")
    val gender: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "date")
    val date: LocalDate,

    @OneToMany(mappedBy = "agency", cascade = [CascadeType.ALL])
    val users: List<Users> = ArrayList<Users>(),
)
{

}