package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.dto.webdto.*
import com.example.eyeserver.contents.repository.UserContentsRepository
import com.example.eyeserver.contents.repository.UserTestRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class WebContentService(
    val userContentsRepository: UserContentsRepository,
    val userTestRepository: UserTestRepository,
) {
    fun dateShowOrderByDate(userId : String) : MutableList<ContentResultDTO>{
        val result = userContentsRepository.findTop5ByUserIdOrderByDateDesc(userId)

        val data = mutableListOf<ContentResultDTO>()
        for (d in result){
            data.add(
                ContentResultDTO(
                    userId= d.userId,
                    contentName = d.contentName,
                    score = d.score,
                    date = d.date,
            )
            )
        }
        return data
    }

    fun dateShowBetweenDate(requestBetweenDateDTO: RequestBetweenDateDTO) : MutableList<ContentResultDTO>{
        val result = userContentsRepository.findByUserIdAndDateBetweenOrderByDateDesc(
            requestBetweenDateDTO.userId,
            requestBetweenDateDTO.startDate,
            requestBetweenDateDTO.endDate,
        )

        val data = mutableListOf<ContentResultDTO>()
        for (d in result){
            data.add(
                ContentResultDTO(
                    userId= d.userId,
                    contentName = d.contentName,
                    score = d.score,
                    date = d.date,
                )
            )
        }
        return data
    }

    fun contentCounter(userId : String) : ResponseContentCountDTO {
        val content1 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content1,userId)
        val content2 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content2,userId)
        val content3 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content3,userId)
        val content4 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content4,userId)
        val content5 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content5,userId)
        val content6 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content6,userId)

        return ResponseContentCountDTO(content1, content2 , content3, content4, content5, content6)
    }

    fun latestAverage(userId : String) : ResponseLatestContentDTO {
        val now = mutableListOf<Double>()
        val latest = mutableListOf<Double>()
        try{
            for (i in UserContents.ContentsName.values()){
                latest.add(userContentsRepository.findAverage(userId, i))
                now.add(userContentsRepository.findTopByUserIdAndContentNameOrderByDateDesc(userId, i).score)
            }
        }
        catch (e : EmptyResultDataAccessException){
            for (i in UserContents.ContentsName.values()){
                latest.add(0.0)
                now.add(0.0)
            }
        }

        return ResponseLatestContentDTO(latest, now)
    }

    fun latestTestALlAverage(userId: String) : ResponseUserTestDTO {
        val avgResult = userTestRepository.findAllAverage(userId)
        val now = listOf<Double>(avgResult[0][0],avgResult[0][1],avgResult[0][2],avgResult[0][3],avgResult[0][4],avgResult[0][5])
        return try{
            val result = userTestRepository.findTopByUserIdOrderByDateDesc(userId)
            val latest = listOf<Double>(result.accurate, result.fixCount, result.questionTime, result.regression, result.saccade, result.totalReadTime)
            ResponseUserTestDTO(latest, now)
        } catch (e : EmptyResultDataAccessException){
            ResponseUserTestDTO(now, listOf(0.0,0.0,0.0,0.0,0.0,0.0))
        }

    }



}