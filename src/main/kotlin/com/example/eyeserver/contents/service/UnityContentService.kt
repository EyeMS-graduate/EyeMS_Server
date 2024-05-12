package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.dto.unitydto.*
import com.example.eyeserver.contents.repository.UserContentsRepository
import com.example.eyeserver.contents.repository.UserTestRepository
import com.example.eyeserver.userLogin.domain.Users
import com.example.eyeserver.userLogin.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth

@Service
class UnityContentService(
    val userContentsRepository: UserContentsRepository,
    val userRepository: UserRepository,
    val userTestRepository: UserTestRepository,
) {
    fun saveUserContentScore(requestUserContentDTO: RequestUserContentDTO): ResponseUserContentDTO {
        val user: Users = userRepository.findByUserId(requestUserContentDTO.userId)
        userContentsRepository.save(
            UserContents(
                userId = requestUserContentDTO.userId,
                contentName = requestUserContentDTO.contentName,
                date = LocalDate.now(),
                score = requestUserContentDTO.score,
                user = user,
                id = null,
                originScore = requestUserContentDTO.originScore,
            )
        )
        return ResponseUserContentDTO(
            success = true,
            message = "good",
        )
    }

    fun getUserContentSummary(userId: String): ResponseSummaryContentDTO {
        val now = mutableListOf<Double>()
        val latest = mutableListOf<Double>()
        val originLatest = mutableListOf<Double>()

        for (i in UserContents.ContentsName.values()) {

            val result = userContentsRepository.findOriginAverage(userId, i);
            if (result[0][0] == null) {
                latest.add(0.0)
                now.add(0.0)
                originLatest.add(0.0)
                continue
            }
            latest.add(result[0][0])
            originLatest.add(result[0][1])
            now.add(userContentsRepository.findTopByUserIdAndContentNameOrderByDateDesc(userId, i).score)

        }



        return ResponseSummaryContentDTO(latest, originLatest, now)
    }

    fun getUserTestSummary(userId: String): ResponseSummaryTestDTO {
        val avgResult = userTestRepository.findAllAverage(userId)
        val latest = listOf<Double>(
            avgResult[0][0],
            avgResult[0][1],
            avgResult[0][2],
            avgResult[0][3],
            avgResult[0][4],
            avgResult[0][5]
        )
        return try {
            val result = userTestRepository.findTopByUserIdOrderByDateDesc(userId)
            val now = listOf<Double>(
                result.accurate,
                result.fixCount,
                result.questionTime,
                result.regression,
                result.saccade,
                result.totalReadTime
            )
            val nowDate = result.date
            ResponseSummaryTestDTO(latest, now, nowDate.toString())
        } catch (e: EmptyResultDataAccessException) {
            ResponseSummaryTestDTO(latest, listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0), "nothing")
        }
    }

    fun getAllDateUserContentCount(userId: String): ResponseDateWithCountDTO {
        val yearMonth = YearMonth.of(LocalDate.now().year, LocalDate.now().month)
        val lastDayOfMonth = yearMonth.lengthOfMonth()

        val startDate = LocalDate.of(LocalDate.now().year, LocalDate.now().month, 1)
        val endDate = LocalDate.of(LocalDate.now().year, LocalDate.now().month, lastDayOfMonth)

        val count = mutableListOf<Int>()
        val date = mutableListOf<LocalDate>()
        var currentDate = startDate
        while (currentDate <= endDate) {
            try {
                val result = userContentsRepository.countByDateAndUserId(currentDate, userId)
                count.add(result)
            } catch (e: Exception) {
                count.add(0)
            }
            date.add(currentDate)

            currentDate = currentDate.plusDays(1)
        }

        return ResponseDateWithCountDTO(date, count)
    }

    fun getRoomId(userId: String): String {
        val result = userRepository.findByUserId(userId).agency.room
        return result.toString()
    }

    fun isCheck(userId: String) = userTestRepository.existsByUserIdAndDate(userId, LocalDate.now())


}