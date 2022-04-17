package me.project.adapter.spring.reactive.config.extension

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// RANDOM_PORT needs HttpHandler
@SpringBootApplication
class SpringTestApplication


fun main(args: Array<String>) {
    runApplication<SpringTestApplication>(*args)
}