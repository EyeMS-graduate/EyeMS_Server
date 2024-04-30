package com.example.eyeserver.contents.domain

import com.example.eyeserver.userLogin.domain.Users
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "usertest")
data class UserTest (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id : Long?,

    @Column(name = "userId")
    val userId : String,

    @Column(name = "fix_count")
    val fixCount : Double,

    @Column(name = "saccade")
    val saccade : Double,

    @Column(name = "total_read_time")
    val totalReadTime : Double,

    @Column(name = "accurate")
    val accurate : Double,

    @Column(name = "regression")
    val regression : Double,

    @Column(name = "question_time")
    val questionTime : Double,

    @Column(name = "date")
    val date : LocalDate,

    @ManyToOne
    @JoinColumn(name = "users")
    var user: Users,

    )