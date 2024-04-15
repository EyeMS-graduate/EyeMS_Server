package com.example.eyeserver.agency.controller

import com.example.eyeserver.agency.dto.ResponseAgencyImageDTO
import com.example.eyeserver.agency.service.AgencyImageService
import com.example.eyeserver.security.JwtTokenCheck
import jakarta.servlet.http.HttpServletRequest
import org.bson.types.Binary
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/agency")
class AgencyImageController (
    private val agencyImageService: AgencyImageService,
){
    @GetMapping("/getImage/{id}")
    fun getAllImage(@PathVariable id : String) : ResponseEntity<Binary> {

        val image = agencyImageService.getImage(id)

        return ResponseEntity.ok(image)
    }

    @PostMapping("/saveImage")
    fun saveAllImage(@RequestParam(value = "files", required = false) files : MultipartFile, ) : ResponseEntity<String>{
        val id =  agencyImageService.addImages(files)
        return ResponseEntity.ok(id)
    }
}