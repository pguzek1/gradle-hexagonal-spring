package org.example.project.ports.driven.query

import java.util.UUID
import org.example.project.ports.model.UserPto

interface IFindUserQueryHandler {
    fun findUser(findUserQueryDto: FindUserQueryDto): UserPto
}

data class FindUserQueryDto(
    val id: UUID
)