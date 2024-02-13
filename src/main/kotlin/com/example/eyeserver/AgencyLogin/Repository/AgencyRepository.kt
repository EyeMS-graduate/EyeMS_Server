package com.example.eyeserver.AgencyLogin.Repository

import com.example.eyeserver.AgencyLogin.Domain.Agency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository : JpaRepository<Agency, String> {
    fun existsByUserId(userId : String) : Boolean

    fun findByUserId(userId: String) : Agency
}