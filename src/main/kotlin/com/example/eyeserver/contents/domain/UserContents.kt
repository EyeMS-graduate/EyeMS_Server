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

    @Column(name = "user_id")
    val userId : String,

    @Column(name = "date")
    val date : LocalDate,

    @Column(name = "content_name")
    @Enumerated(value = EnumType.STRING)
    val contentName : ContentsName,

    @Column(name = "score")
    val score : Double,

    @Column(name = "origin_score")
    val originScore : Double = 10.0,

    @ManyToOne
    @JoinColumn(name = "users")
    var user: Users? = null,
) {

    enum class ContentsName{
        글자기억하기,
        과일색맞추기,
        토끼를찾아라,
        동물찾기,
        물고기사냥,
        돌고래의모험,
    }
}