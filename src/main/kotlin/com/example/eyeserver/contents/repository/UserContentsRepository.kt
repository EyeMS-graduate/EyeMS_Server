package com.example.eyeserver.contents.repository

import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.domain.UserTest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface UserContentsRepository : JpaRepository<UserContents, Long>{

    fun findTop5ByUserIdOrderByDateDesc(userId : String) : List<UserContents>


    fun countByContentNameAndUserId(contentName : UserContents.ContentsName, userId : String,) : Long

    @Query("SELECT AVG(uc.score) FROM usercontents uc WHERE uc.userId = :userId AND uc.contentName = :contentName ORDER BY uc.date DESC limit 5")
    fun findAverage(@Param(value = "userId") userId: String, @Param(value = "contentName") contentName: UserContents.ContentsName) : Double

    @Query("SELECT AVG(uc.originScore) as avgOriginScore, AVG(uc.score) as avgScore FROM usercontents uc WHERE uc.userId = :userId AND uc.contentName = :contentName ORDER BY uc.date DESC limit 5")
    fun findOriginAverage(@Param(value = "userId") userId: String, @Param(value = "contentName") contentName: UserContents.ContentsName) : List<Array<Double>>

    fun findTopByUserIdAndContentNameOrderByDateDesc(userId: String, contentName: UserContents.ContentsName) : UserContents

    fun countByDateAndUserId(date : LocalDate, userId : String) : Int

    @Query("select uc from usercontents uc left join uc.user.agency ua where ua.agencyId =:agencyId and uc.contentName = :contentName and uc.date between :startDate and :endDate")
    fun getContentPlay(@Param(value = "agencyId")agencyId : String ,@Param(value = "contentName")contentName : UserContents.ContentsName, @Param(value = "startDate")startDate : LocalDate, @Param(value = "endDate")endDate : LocalDate) : List<Array<UserContents>>


    @Query("select uc from usercontents uc left join uc.user.agency ua where ua.agencyId =:agencyId order by uc.date desc")
    fun findAllUserContentPlay(@Param(value = "agencyId")agencyId : String) : List<Array<UserContents>>
}