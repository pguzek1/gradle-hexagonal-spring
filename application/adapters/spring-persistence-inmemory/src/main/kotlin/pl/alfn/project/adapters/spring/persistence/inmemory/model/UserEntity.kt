package pl.alfn.project.adapters.spring.persistence.inmemory.model

import java.time.Instant
import java.util.UUID

data class UserEntity(
    val firstName: String,
    val lastName: String,
    override val id: UUID,
    override val version: Long,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : AbstractEntity