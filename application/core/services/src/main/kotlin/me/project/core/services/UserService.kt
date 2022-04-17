package me.project.core.services

import java.util.UUID
import me.project.core.domain.model.User
import me.project.core.domain.validation.validate
import me.project.core.services.converter.query.toDriven
import me.project.core.services.converter.domain.toPto
import me.project.ports.driven.command.IPersistUserCommandHandler
import me.project.ports.driven.query.IFindUserQueryHandler
import me.project.ports.driving.command.IPersistUserCommandUseCase
import me.project.ports.driving.command.PersistUserCommandDto
import me.project.ports.driving.query.FindUserQueryDto
import me.project.ports.driving.query.IFindUserQueryUseCase
import me.project.ports.model.UserPto

class UserService(
    private val persistUserCommandHandler: IPersistUserCommandHandler,
    private val findUserQueryHandler: IFindUserQueryHandler
) : IPersistUserCommandUseCase, IFindUserQueryUseCase {

    override fun createUser(persistUserCommandDto: PersistUserCommandDto): UUID {
        val user = User(
            firstName = persistUserCommandDto.firstName,
            lastName = persistUserCommandDto.lastName,
            persistenceInfo = null
        )

        user.validate()

        return persistUserCommandHandler.persistUser(user.toPto())
    }

    override fun findUser(findUserQueryDto: FindUserQueryDto): UserPto {
        return findUserQueryHandler.findUser(findUserQueryDto.toDriven())
    }

}