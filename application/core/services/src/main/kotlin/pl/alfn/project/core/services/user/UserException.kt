package pl.alfn.project.core.services.user

import java.util.UUID

open class UserException(message: String) : RuntimeException(message)

class UserNotFoundException(uuid: UUID) : UserException("User '$uuid' not found")
