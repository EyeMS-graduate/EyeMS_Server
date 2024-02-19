package com.example.eyeserver.security


import com.example.eyeserver.agencyLogin.dto.TokenResponseDTO
import com.example.eyeserver.agencyLogin.role.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date


@Component
class JwtTokenProvider (
    private val userDetailService: UserDetailsService
){


    val secretKey: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun createToken(
        userId : String,
        role : Role,
        agency : String,
        ): TokenResponseDTO {
        val claims = Jwts.claims().setSubject(role.name)
        claims["userId"] = userId
        claims["agency"] = agency
        println(secretKey)
        println("----------------------------")
        val now = Date()
        val utcExpirationDate = Date(now.time + TOKEN_VALID_MILLISECOND)

        return TokenResponseDTO(
            token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(utcExpirationDate)
                .signWith(secretKey)
                .compact(),
            utcExpirationDate = utcExpirationDate,
        )
    }

    //Header에서 token 값 가져옴
    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader(TOKEN_HEADER_NAME)
    }

    //유효성 + 만료일자 확인
    fun validateToken(jwtToken: String): Boolean {
        println(secretKey)
        return try {
            //val token = jwtToken.substring(7)
            val claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)

            claimsJws
                .body
                .expiration
                .after(Date())

        } catch (e: Exception) {
            println(e)
            false
        }
    }

    fun userPrimaryKey(jwtToken: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey).build()
            .parseClaimsJws(jwtToken)
            .body
    }

    @Transactional
    fun userAuthentication(jwtToken: String): Authentication {
        val userDetails = userDetailService
            .loadUserByUsername(userPrimaryKey(jwtToken).subject)

        return UsernamePasswordAuthenticationToken(
            userDetails, userDetails.password, userDetails.authorities
        )
    }



    companion object {
        private const val TOKEN_VALID_MILLISECOND = 1000L * 60 * 60
        private const val TOKEN_HEADER_NAME = "Authorization"
    }
}