package org.example.project.core.domain.validation

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.example.project.core.domain.config.ValidationSpec
import org.example.project.core.domain.model.User
import org.example.project.core.domain.validation.exception.ValidationException

class UserValidationSpec : ValidationSpec() {

    init {

        this.should("throw exception when firstname starts with lowercase") {
            val user = User(
                firstName = "jan",
                lastName = "Kowalski",
                persistenceInfo = null
            )

            shouldThrow<ValidationException> { user.validate() }
        }

        this.should("throw exception when lastname starts with lowercase") {
            val user = User(
                firstName = "Jan",
                lastName = "kowalski",
                persistenceInfo = null
            )

            shouldThrow<ValidationException> { user.validate() }
        }

        this.should("throw not throw exception when firstname and lastname capitalized") {
            val user = User(
                firstName = "Jan",
                lastName = "Kowalski",
                persistenceInfo = null
            )

            shouldNotThrow<ValidationException> { user.validate() }
        }

    }

}