package com.example.eyeserver.Login.Repository

import com.example.eyeserver.Login.Domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {

}