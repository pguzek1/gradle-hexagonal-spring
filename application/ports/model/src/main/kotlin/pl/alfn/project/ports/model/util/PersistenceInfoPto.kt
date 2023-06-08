package pl.alfn.project.ports.model.util

import java.time.Instant
import java.util.UUID

data class PersistenceInfoPto(
    val id: UUID,
    val version: Long,
    val createdAt: Instant,
    val updatedAt: Instant
)