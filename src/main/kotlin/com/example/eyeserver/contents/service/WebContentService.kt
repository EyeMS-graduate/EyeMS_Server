package com.example.eyeserver.contents.service

import com.example.eyeserver.contents.dto.ContentResultDTO
import com.example.eyeserver.contents.dto.RequestBetweenDateDTO
import com.example.eyeserver.contents.dto.RequestUserContentDTO
import com.example.eyeserver.contents.dto.ResponseContentCountDTO
import com.example.eyeserver.contents.repository.UserContentsRepository
import org.springframework.stereotype.Service

@Service
class WebContentService(
    val userContentsRepository: UserContentsRepository,
) {
    fun dateShowOrderByDate(userId : String) : MutableList<ContentResultDTO>{
        val result = userContentsRepository.findByUserIdOrderByDateDesc(userId)

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

    /*fun contentCounter() : ResponseContentCountDTO{

    }*/

}