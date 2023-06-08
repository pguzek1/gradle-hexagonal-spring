package pl.alfn.project.adapters.spring.persistence.inmemory.model

import java.time.Instant
import java.util.UUID

interface AbstractEntity {
    val id: UUID
    val version: Long
    val createdAt: Instant
    val updatedAt: Instant
}
