package me.project.adapter.spring.reactive.adapter

import java.util.UUID
import me.project.adapter.spring.reactive.converter.toDto
import me.project.adapter.spring.reactive.criteria.CreateUserCriteria
import me.project.adapter.spring.reactive.dto.UserDto
import me.project.ports.driving.command.IPersistUserCommandUseCase
import me.project.ports.driving.command.PersistUserCommandDto
import me.project.ports.driving.query.FindUserQueryDto
import me.project.ports.driving.query.IFindUserQueryUseCase
import org.springframework.stereotype.Component

@Component
class UserAdapter(
    private val persistUserCommandUseCase: IPersistUserCommandUseCase,
    private val findUserQueryUseCase: IFindUserQueryUseCase,
) {

    fun persistUser(createUserCriteria: CreateUserCriteria) : UUID {
        return persistUserCommandUseCase.createUser(
            PersistUserCommandDto(
                firstName = createUserCriteria.firstName,
                lastName = createUserCriteria.lastName,
            )
        )
    }

    fun findUser(id: UUID): UserDto {
        return findUserQueryUseCase.findUser(
            FindUserQueryDto(
                id = id
            )
        ).toDto()
    }

}
