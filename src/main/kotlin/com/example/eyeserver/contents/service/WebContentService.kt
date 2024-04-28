package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.domain.UserContents
import com.example.eyeserver.contents.dto.*
import com.example.eyeserver.contents.repository.UserContentsRepository
import org.springframework.stereotype.Service

@Service
class WebContentService(
    val userContentsRepository: UserContentsRepository,
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

    fun contentCounter(userId : String) : ResponseContentCountDTO{
        val content1 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content1,userId)
        val content2 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content2,userId)
        val content3 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content3,userId)
        val content4 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content4,userId)
        val content5 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content5,userId)
        val content6 = userContentsRepository.countByContentNameAndUserId(UserContents.ContentsName.Content6,userId)

        return ResponseContentCountDTO(content1, content2 , content3, content4, content5, content6)
    }

}