package org.example.project.runnable.spring.configuration

import org.example.project.core.services.UserService
import org.example.project.ports.driven.command.IPersistUserCommandHandler
import org.example.project.ports.driven.query.IFindUserQueryHandler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoreServiceConfiguration(
    @Qualifier("findUserQueryHandler") private val findUserQueryHandler: IFindUserQueryHandler,
    @Qualifier("userRepository") private val persistUserCommandHandler: IPersistUserCommandHandler,
) {

    @Bean
    fun userUseCase(): UserService {
        return UserService(
            findUserQueryHandler = findUserQueryHandler,
            persistUserCommandHandler = persistUserCommandHandler,
        )
    }
}