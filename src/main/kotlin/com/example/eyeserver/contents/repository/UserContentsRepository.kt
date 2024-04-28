package com.example.eyeserver.contents.repository

import com.example.eyeserver.contents.domain.UserContents
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface UserContentsRepository : JpaRepository<UserContents, Long>{

    fun findTop5ByUserIdOrderByDateDesc(userId : String) : List<UserContents>

    fun findByUserIdAndDateBetweenOrderByDateDesc(userId : String, startDate : LocalDate, endDate : LocalDate) : List<UserContents>

    fun countByContentNameAndUserId(contentName : UserContents.ContentsName, userId : String,) : Long


}