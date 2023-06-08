package pl.alfn.project.adapters.spring.persistence.inmemory.exception

open class RepositoryException(message: String) : RuntimeException(message)

class OptimisticLockException(message: String) : RepositoryException(message)

class UserNotFoundException(message: String) : RepositoryException(message)