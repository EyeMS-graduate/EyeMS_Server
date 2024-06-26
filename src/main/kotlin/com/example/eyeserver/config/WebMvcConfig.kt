package com.example.eyeserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer{



    override fun addCorsMappings(corsRegistry: CorsRegistry){
        corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "*")
            .allowedMethods("GET", "POST", "DELETE")
    }


}