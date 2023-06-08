package pl.alfn.project.core.domain

open class ValidationException(message: String) : RuntimeException(message)

class UserValidationException(message: String) : ValidationException(message)