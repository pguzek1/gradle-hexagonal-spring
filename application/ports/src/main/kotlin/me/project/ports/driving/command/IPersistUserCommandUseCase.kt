package me.project.ports.driving.command

import java.util.UUID

interface IPersistUserCommandUseCase {
    fun createUser(persistUserCommandDto: PersistUserCommandDto): UUID
}

data class PersistUserCommandDto(
    val firstName: String,
    val lastName: String,
)