package me.project.ports.model.util

import java.util.UUID

data class PersistenceInfoPto(
    val id: UUID,
    val version: Long,
)