package me.project.adapter.spring.reactive.dto

import java.util.UUID

data class UserDto(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val version: Long,
)