package pl.alfn.project.core.domain.util

import java.time.Instant
import java.util.UUID

data class PersistenceInfo(
    val id: UUID,
    val version: Long,
    val createdAt: Instant,
    val updatedAt: Instant
)