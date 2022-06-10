package org.example.project.runnable.spring.configuration

import org.example.project.adapter.persistence.memory.MemoryPersistenceAutoConfiguration
import org.example.project.ports.driven.command.IPersistUserCommandHandler
import org.example.project.ports.driven.query.IFindUserQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MemoryPersistenceAdapterConfiguration {

    private val memoryPersistenceAutoConfiguration = MemoryPersistenceAutoConfiguration()

    @Bean
    fun findUserQueryHandler() : IFindUserQueryHandler {
        return memoryPersistenceAutoConfiguration.userRepository
    }

    @Bean
    fun userRepository() : IPersistUserCommandHandler {
        return memoryPersistenceAutoConfiguration.userRepository
    }

}