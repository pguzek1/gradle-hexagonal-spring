package me.project.ports.model

import me.project.ports.model.util.PersistenceInfoPto

data class UserPto(
    val firstName: String,
    val lastName: String,
    val persistenceInfo: PersistenceInfoPto?
)