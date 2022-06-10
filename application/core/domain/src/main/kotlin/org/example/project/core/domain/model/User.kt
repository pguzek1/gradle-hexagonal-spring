package org.example.project.core.domain.model

import org.example.project.core.domain.model.util.PersistenceInfo
import mu.KotlinLogging

data class User(
    val firstName: String,
    val lastName: String,
    val persistenceInfo: PersistenceInfo?,
) {
    companion object {
        val logger = KotlinLogging.logger {}
    }
}
