package com.example.eyeserver.agency.repository

import com.example.eyeserver.agency.domain.Agency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository : JpaRepository<Agency, String> {

    fun existsByAgencyId(agencyId : String) : Boolean

    fun findByAgencyId(agencyId: String) :  Agency
    fun findByAgencyName(agencyName : String) : Agency

}