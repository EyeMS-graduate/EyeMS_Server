package com.example.eyeserver.unityContent.controller

import com.example.eyeserver.unityContent.dto.InfoDTO
import com.example.eyeserver.unityContent.dto.InfoRequestDTO
import com.example.eyeserver.unityContent.service.InfoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
class InfoController(
    val infoService: InfoService
) {

    @PostMapping("/get")
    fun sendInfo(@RequestBody infoRequestDTO: InfoRequestDTO): ResponseEntity<InfoDTO?>{
        return ResponseEntity.ok().body(infoService.sendInfo(infoRequestDTO))
    }

    @PostMapping("/set")
    fun receiveInfo(@RequestBody infoDTO: InfoDTO): ResponseEntity<Boolean>{
        return ResponseEntity.ok().body(infoService.receiveInfo(infoDTO))
    }
}