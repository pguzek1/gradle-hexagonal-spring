package pl.alfn.project.adapters.spring.persistence.inmemory.repository

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import pl.alfn.project.adapters.spring.persistence.inmemory.exception.OptimisticLockException
import pl.alfn.project.adapters.spring.persistence.inmemory.exception.UserNotFoundException
import pl.alfn.project.adapters.spring.persistence.inmemory.model.UserEntity
import pl.alfn.project.adapters.spring.persistence.inmemory.model.converter.toEntity
import pl.alfn.project.adapters.spring.persistence.inmemory.model.converter.toPto
import pl.alfn.project.ports.driven.user.IUserFindQueryDriven
import pl.alfn.project.ports.driven.user.IUserPersistCommandDriven
import pl.alfn.project.ports.model.user.FindUserQueryDriven
import pl.alfn.project.ports.model.user.UserPto

class UserInMemoryDatabaseRepository : IUserFindQueryDriven, IUserPersistCommandDriven {
    private val userRepository = ConcurrentHashMap<UUID, UserEntity>()

    override suspend fun findUser(findUserQuery: FindUserQueryDriven): UserPto =
        userRepository[findUserQuery.id]?.toPto() ?: throw UserNotFoundException("User '${findUserQuery.id}' not found")

    override suspend fun persistUser(user: UserPto): UUID {
        return userRepository
            .compute(user.persistenceInfo.id) { _, oldEntity ->
                val newEntity = user.toEntity()
                if (oldEntity == null || oldEntity.version == (user.persistenceInfo.version - 1))
                    newEntity
                else
                    throw OptimisticLockException("Version mismatch while overriding oldEntity[$oldEntity] with newEntity[$newEntity]")
            }
            ?.id!!
    }
}