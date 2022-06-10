package org.example.project.ports.model

import org.example.project.ports.model.util.PersistenceInfoPto

data class UserPto(
    val firstName: String,
    val lastName: String,
    val persistenceInfo: PersistenceInfoPto?
)