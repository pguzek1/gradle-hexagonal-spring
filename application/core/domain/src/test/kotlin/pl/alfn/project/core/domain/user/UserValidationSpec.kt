package pl.alfn.project.core.domain.user

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import java.time.Instant.now
import java.util.UUID
import pl.alfn.project.core.domain.UserValidationException
import pl.alfn.project.core.domain.ValidationSpec
import pl.alfn.project.core.domain.util.PersistenceInfo

class UserValidationSpec : ValidationSpec({

    val samplePersistenceInfo = PersistenceInfo(
        id = UUID.randomUUID(),
        version = 0,
        createdAt = now(),
        updatedAt = now()
    )

    should("throw exception when firstname starts with lowercase") {
        val user = User(
            firstName = "jan",
            lastName = "Kowalski",
            persistenceInfo = samplePersistenceInfo
        )

        shouldThrow<UserValidationException> { user.validate() }
            .shouldHaveMessage("firstname should start with uppercase letter")
    }

    should("throw exception when firstname is empty") {
        val user = User(
            firstName = "",
            lastName = "Kowalski",
            persistenceInfo = samplePersistenceInfo
        )

        shouldThrow<UserValidationException> { user.validate() }
            .shouldHaveMessage("firstName should not be empty")
    }

    should("throw exception when lastname starts with lowercase") {
        val user = User(
            firstName = "Jan",
            lastName = "kowalski",
            persistenceInfo = samplePersistenceInfo
        )

        shouldThrow<UserValidationException> { user.validate() }
            .shouldHaveMessage("lastName should start with uppercase letter")
    }

    should("throw exception when lastname is empty") {
        val user = User(
            firstName = "Jan",
            lastName = "",
            persistenceInfo = samplePersistenceInfo
        )

        shouldThrow<UserValidationException> { user.validate() }
            .shouldHaveMessage("lastName should not be empty")
    }

    should("throw not throw exception when firstname and lastname capitalized") {
        val user = User(
            firstName = "Jan",
            lastName = "Kowalski",
            persistenceInfo = samplePersistenceInfo
        )

        shouldNotThrow<UserValidationException> { user.validate() }
    }
})