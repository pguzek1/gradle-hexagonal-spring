package org.example.project.core.services

import java.util.UUID
import org.example.project.core.domain.model.User
import org.example.project.core.domain.validation.validate
import org.example.project.core.services.converter.query.toDriven
import org.example.project.core.services.converter.domain.toPto
import org.example.project.ports.driven.command.IPersistUserCommandHandler
import org.example.project.ports.driven.query.IFindUserQueryHandler
import org.example.project.ports.driving.command.IPersistUserCommandUseCase
import org.example.project.ports.driving.command.PersistUserCommandDto
import org.example.project.ports.driving.query.FindUserQueryDto
import org.example.project.ports.driving.query.IFindUserQueryUseCase
import org.example.project.ports.model.UserPto

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