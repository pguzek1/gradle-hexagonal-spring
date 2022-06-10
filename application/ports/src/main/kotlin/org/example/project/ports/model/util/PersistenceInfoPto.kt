package org.example.project.ports.model.util

import java.util.UUID

data class PersistenceInfoPto(
    val id: UUID,
    val version: Long,
)