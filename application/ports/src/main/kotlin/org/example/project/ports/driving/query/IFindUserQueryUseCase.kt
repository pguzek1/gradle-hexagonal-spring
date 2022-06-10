package org.example.project.ports.driving.query

import java.util.UUID
import org.example.project.ports.model.UserPto

interface IFindUserQueryUseCase {
    fun findUser(findUserQueryDto: FindUserQueryDto): UserPto
}

data class FindUserQueryDto(
    val id: UUID
)