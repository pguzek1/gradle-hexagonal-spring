package me.project.ports.driven.query

import java.util.UUID
import me.project.ports.model.UserPto

interface IFindUserQueryHandler {
    fun findUser(findUserQueryDto: FindUserQueryDto): UserPto
}

data class FindUserQueryDto(
    val id: UUID
)