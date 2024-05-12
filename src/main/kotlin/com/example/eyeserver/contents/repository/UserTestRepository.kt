package com.example.eyeserver.contents.repository

import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.domain.UserTest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface UserTestRepository : JpaRepository<UserTest, Long> {

    fun findTopByUserIdOrderByDateDesc(userId: String) : UserTest

    @Query("SELECT AVG(ut.accurate) , AVG (ut.fixCount),AVG (ut.questionTime),AVG (ut.regression),AVG (ut.saccade) ,AVG (ut.totalReadTime) " +
            " FROM usertest ut")
    fun findAllAverage(@Param(value = "userId") userId: String) : List<Array<Double>>

    fun findTop5ByUserIdOrderByDateDesc(userId : String) : List<UserTest>

    fun findByUserIdAndDateBetweenOrderByDateDesc(userId : String, startDate : LocalDate, endDate : LocalDate) : List<UserTest>

    fun existsByUserIdAndDate(userId : String ,date : LocalDate) : Boolean
}