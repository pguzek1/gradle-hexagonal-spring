package pl.alfn.project.adapters.spring.persistence.inmemory.model.converter

import pl.alfn.project.adapters.spring.persistence.inmemory.model.UserEntity
import pl.alfn.project.ports.model.user.UserPto
import pl.alfn.project.ports.model.util.PersistenceInfoPto


fun UserEntity.toPto() =
    UserPto(
        firstName = firstName,
        lastName = lastName,
        persistenceInfo = PersistenceInfoPto(
            id = id,
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    )

fun UserPto.toEntity() =
    UserEntity(
        id = persistenceInfo.id,
        firstName = firstName,
        lastName = lastName,
        version = persistenceInfo.version,
        createdAt = persistenceInfo.createdAt,
        updatedAt = persistenceInfo.updatedAt
    )