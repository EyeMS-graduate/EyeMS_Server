package com.example.eyeserver.userLogin.repository

import com.example.eyeserver.userLogin.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Users, String>{

    fun findByUserId(id: String): Users
    fun existsByUserId(id : String) : Boolean
    fun deleteByUserId(id : String)

    fun findByUserIdContainsAndAgency_AgencyId(userId: String, agency_agencyId: String) : List<Users>
}