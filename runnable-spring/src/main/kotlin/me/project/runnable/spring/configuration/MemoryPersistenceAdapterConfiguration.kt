package me.project.runnable.spring.configuration

import me.project.adapter.persistence.memory.MemoryPersistenceAutoConfiguration
import me.project.ports.driven.command.IPersistUserCommandHandler
import me.project.ports.driven.query.IFindUserQueryHandler
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
    fun userRepository2() : IPersistUserCommandHandler {
        return memoryPersistenceAutoConfiguration.userRepository
    }

}