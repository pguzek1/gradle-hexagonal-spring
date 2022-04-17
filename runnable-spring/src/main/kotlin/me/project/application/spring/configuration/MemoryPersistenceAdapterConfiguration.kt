package me.project.application.spring.configuration

import me.project.adapter.persistence.memory.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MemoryPersistenceAdapterConfiguration {

    @Bean
    fun userRepository() : UserRepository {
        return UserRepository()
    }

}