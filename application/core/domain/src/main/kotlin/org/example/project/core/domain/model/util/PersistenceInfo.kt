package org.example.project.core.domain.model.util

import java.util.UUID

data class PersistenceInfo(
    val id: UUID,
    val version: Long,
)
