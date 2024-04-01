package com.example.eyeserver.imsi

import com.example.eyeserver.imsi.domain.UnityData
import org.springframework.data.jpa.repository.JpaRepository

interface UnityDataRepository : JpaRepository<UnityData, String > {
    fun findByUserId(userId : String) : UnityData
}