package org.example.project.core.services.converter.domain

import org.example.project.core.domain.model.User
import org.example.project.core.services.converter.domain.util.toDomain
import org.example.project.core.services.converter.domain.util.toPto
import org.example.project.ports.model.UserPto

fun User.toPto(): UserPto {
    return UserPto(
        firstName = firstName,
        lastName = lastName,
        persistenceInfo = persistenceInfo?.toPto(),
    )
}

fun UserPto.toDomain(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        persistenceInfo = persistenceInfo?.toDomain(),
    )
}