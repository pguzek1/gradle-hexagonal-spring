package me.project.core.domain.model

import me.project.core.domain.model.util.PersistenceInfo
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
