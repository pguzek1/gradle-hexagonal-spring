package org.example.project.core.domain.validation

import org.example.project.core.domain.model.User
import org.example.project.core.domain.model.User.Companion.logger
import org.example.project.core.domain.validation.exception.ValidationException

fun User.validate() {
    logger.info { "validation user object" }
    validateFirstName()
    validateLastName()
}

private fun User.validateFirstName() {
    if (firstName[0] == firstName[0].lowercaseChar()) {
        logger.warn { "firstname is not capitalized" }
        throw ValidationException(
            message = "firstname should start with uppercase letter"
        )
    }
}

private fun User.validateLastName() {
    if (lastName[0] == lastName[0].lowercaseChar()) {
        logger.warn { "lastname is not capitalized" }
        throw ValidationException(
            "lastname should start with uppercase letter"
        )
    }
}
