package com.example.eyeserver.userLogin.domain

import com.example.eyeserver.agency.domain.Agency
import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.domain.UserTest
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Users (

    @Id
    @Column(name = "userId")
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
    val date : LocalDate,

    @ManyToOne
    @JoinColumn(name = "agency")
    var agency: Agency,

    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL])
    val contentsResult: List<UserContents> = ArrayList<UserContents>(),

    @OneToMany(mappedBy = "userId", cascade = [CascadeType.ALL])
    val contentsTest: List<UserTest> = ArrayList<UserTest>()

)