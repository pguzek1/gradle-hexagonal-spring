package pl.alfn.project.adapters.spring.persistence.inmemory

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.alfn.project.adapters.spring.persistence.inmemory.repository.UserInMemoryDatabaseRepository

@Configuration
class InMemoryPersistenceAdapterAutoConfiguration {

    @Bean
    fun inMemoryDatabaseAdapter(): UserInMemoryDatabaseRepository =
        UserInMemoryDatabaseRepository()

}