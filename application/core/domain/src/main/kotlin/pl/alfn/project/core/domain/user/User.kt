package pl.alfn.project.core.domain.user

import mu.KotlinLogging.logger
import pl.alfn.project.core.domain.UserValidationException
import pl.alfn.project.core.domain.util.PersistenceInfo

data class User(
    val firstName: String,
    val lastName: String,
    val persistenceInfo: PersistenceInfo,
) {
    fun validate() {
        logger.debug { "validation user object: $this" }
        validateFirstName()
        validateLastName()
    }

    private fun validateFirstName() {
        if (firstName.isEmpty()) {
            logger.trace { "firstName is empty" }
            throw UserValidationException("firstName should not be empty")
        }
        if (firstName[0] == firstName[0].lowercaseChar()) {
            logger.trace { "firstname is not capitalized" }
            throw UserValidationException("firstname should start with uppercase letter")
        }
    }

    private fun validateLastName() {
        if (lastName.isEmpty()) {
            logger.trace { "lastName is empty" }
            throw UserValidationException("lastName should not be empty")
        }
        if (lastName[0] == lastName[0].lowercaseChar()) {
            logger.trace { "lastName is not capitalized" }
            throw UserValidationException("lastName should start with uppercase letter")
        }
    }

    companion object {
        val logger = logger {}
    }
}