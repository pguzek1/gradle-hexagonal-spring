package org.example.project.adapter.spring.reactive.converter

import org.example.project.adapter.spring.reactive.dto.UserDto
import org.example.project.ports.model.UserPto

fun UserPto.toDto() : UserDto {
    return UserDto(
        id = persistenceInfo!!.id,
        firstName = firstName,
        lastName = lastName,
        version = persistenceInfo!!.version,
    )
}