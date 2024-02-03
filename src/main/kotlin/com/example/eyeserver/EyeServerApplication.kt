package com.example.eyeserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EyeServerApplication

fun main(args: Array<String>) {
	runApplication<EyeServerApplication>(*args)
}
