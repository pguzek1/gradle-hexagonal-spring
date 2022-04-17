package me.project.application.spring.configuration

import me.project.core.services.UserService
import me.project.ports.driven.command.IPersistUserCommandHandler
import me.project.ports.driven.query.IFindUserQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoreServiceConfiguration(
    private val findUserQueryHandler: IFindUserQueryHandler,
    private val persistUserCommandHandler: IPersistUserCommandHandler,
) {

    @Bean
    fun userUseCase(): UserService {
        return UserService(
            findUserQueryHandler = findUserQueryHandler,
            persistUserCommandHandler = persistUserCommandHandler,
        )
    }
}