package me.project.adapter.spring.reactive

import io.kotest.matchers.shouldBe
import me.project.adapter.spring.reactive.config.SpringBootSpec
import org.springframework.test.web.reactive.server.expectBody

class UserEndpointSpec : SpringBootSpec() {

    init {

        this.should("get hello world") {
            webTestClient.get()
                .uri("/user")
                .exchange()
                .expectStatus()
                .isOk
                .expectBody<String>()
                .consumeWith {
                    it.responseBody shouldBe "Hello world"
                }
        }

    }

}