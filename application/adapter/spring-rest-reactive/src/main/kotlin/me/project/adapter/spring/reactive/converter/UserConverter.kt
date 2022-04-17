package me.project.adapter.spring.reactive.converter

import me.project.adapter.spring.reactive.dto.UserDto
import me.project.ports.model.UserPto

fun UserPto.toDto() : UserDto {
    return UserDto(
        id = persistenceInfo!!.id,
        firstName = firstName,
        lastName = lastName,
        version = persistenceInfo!!.version,
    )
}