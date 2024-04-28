package com.example.eyeserver.contents.domain

import com.example.eyeserver.agency.domain.Agency
import com.example.eyeserver.userLogin.domain.Users
import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity(name = "usercontents")

data class UserContents(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id : Long?,

    @Column(name = "userId")
    val userId : String,

    @Column(name = "date")
    val date : LocalDate,

    @Column(name = "content_name")
    val contentName : ContentsName,

    @Column(name = "score")
    val score : Double,

    @ManyToOne
    @JoinColumn(name = "users")
    var user: Users ,
) {

    enum class ContentsName{
        Content1,
        Content2,
        Content3,
        Content4,
        Content5,
        Content6,
    }
}