package me.project.ports.driving.query

import java.util.UUID
import me.project.ports.model.UserPto

interface IFindUserQueryUseCase {
    fun findUser(findUserQueryDto: FindUserQueryDto): UserPto
}

data class FindUserQueryDto(
    val id: UUID
)