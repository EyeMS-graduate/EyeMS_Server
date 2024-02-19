package com.example.eyeserver.agencyLogin.repository

import com.example.eyeserver.agencyLogin.domain.Agency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository : JpaRepository<Agency, String> {
    fun existsByAgencyId(userId : String) : Boolean

    fun findByAgencyId(userId: String) : Agency
}