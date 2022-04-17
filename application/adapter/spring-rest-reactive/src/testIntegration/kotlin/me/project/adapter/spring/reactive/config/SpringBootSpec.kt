package me.project.adapter.spring.reactive.config

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.ShouldSpec
import me.project.adapter.spring.reactive.SpringRestReactiveAutoConfiguration
import me.project.adapter.spring.reactive.config.extension.SpringTestApplication
import me.project.ports.driving.command.IPersistUserCommandUseCase
import me.project.ports.driving.query.IFindUserQueryUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.web.reactive.server.WebTestClient

@AutoConfigureWebTestClient
@SpringBootTest(
    classes = [SpringTestApplication::class, SpringRestReactiveAutoConfiguration::class],
    webEnvironment = RANDOM_PORT
)
class SpringBootSpec : ShouldSpec() {

    @MockkBean
    protected lateinit var persistenceUserCommandUseCase: IPersistUserCommandUseCase

    @MockkBean
    protected lateinit var findUserQueryUseCase: IFindUserQueryUseCase

    @Autowired
    protected lateinit var webTestClient: WebTestClient

}