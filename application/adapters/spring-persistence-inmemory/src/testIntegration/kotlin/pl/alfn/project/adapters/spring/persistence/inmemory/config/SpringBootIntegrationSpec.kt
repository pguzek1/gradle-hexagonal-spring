package pl.alfn.project.adapters.spring.persistence.inmemory.config

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.test.TestScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import pl.alfn.project.adapters.spring.persistence.inmemory.InMemoryPersistenceAdapterAutoConfiguration


@SpringBootTest(
    classes = [TestApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(
    classes = [InMemoryPersistenceAdapterAutoConfiguration::class]
)
abstract class SpringBootIntegrationSpec : ShouldSpec() {

    init {
        TestContainers.attach()
    }

    @Autowired
    lateinit var request: TestRestTemplate
    final override fun should(name: String, test: suspend TestScope.() -> Unit) = super.should(name, test)
    final override fun xshould(name: String, test: suspend TestScope.() -> Unit) = super.xshould(name, test)
}

object TestContainers {
    const val MONGO_INTERNAL_PORT = 27017
    const val MARIA_INTERNAL_PORT = 3306

    val mongoDBContainer: MongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:6"))
        .withExposedPorts(MONGO_INTERNAL_PORT) // added automatically

    val mariaDBContainer: MariaDBContainer<*> = MariaDBContainer(DockerImageName.parse("mariadb:10"))
        .withExposedPorts(MARIA_INTERNAL_PORT) // added automatically

    fun attach() {
        runBlocking {
            withContext(coroutineContext) {
                async { mongoDBContainer.start() }
                async { mariaDBContainer.start() }
                this
            }
        }
//        Stream.of(mongoDBContainer, mariaDBContainer)
//            .parallel()
//            .forEach { it.start() }

//        mongoDBContainer.start()
//        mariaDBContainer.start()
    }
}