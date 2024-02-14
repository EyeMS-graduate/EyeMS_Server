package com.example.eyeserver.agencyLogin.repository

import com.example.eyeserver.agencyLogin.domain.Agency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository : JpaRepository<Agency, String> {
    fun existsByUserId(userId : String) : Boolean

    fun findByUserId(userId: String) : Agency
}