package com.example.eyeserver.contents.repository

import com.example.eyeserver.contents.domain.UserContents
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface UserContentsRepository : JpaRepository<UserContents, Long>{

    fun findTop5ByUserIdOrderByDateDesc(userId : String) : List<UserContents>

    fun findByUserIdAndDateBetweenOrderByDateDesc(userId : String, startDate : LocalDate, endDate : LocalDate) : List<UserContents>


    fun countByContentNameAndUserId(contentName : UserContents.ContentsName, userId : String,) : Long

    @Query("SELECT AVG(uc.score) FROM usercontents uc WHERE uc.userId = :userId AND uc.contentName = :contentName ORDER BY uc.date DESC limit 5")
    fun findAverage(@Param(value = "userId") userId: String, @Param(value = "contentName") contentName: UserContents.ContentsName) : Double

    @Query("SELECT AVG(uc.originScore) as avgOriginScore, AVG(uc.score) as avgScore FROM usercontents uc WHERE uc.userId = :userId AND uc.contentName = :contentName ORDER BY uc.date DESC limit 5")
    fun findOriginAverage(@Param(value = "userId") userId: String, @Param(value = "contentName") contentName: UserContents.ContentsName) : List<Array<Double>>

    fun findTopByUserIdAndContentNameOrderByDateDesc(userId: String, contentName: UserContents.ContentsName) : UserContents

    fun countByDateAndUserId(date : LocalDate, userId : String) : Int


}