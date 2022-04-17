package me.project.core.services.converter.domain

import me.project.core.domain.model.User
import me.project.core.services.converter.domain.util.toDomain
import me.project.core.services.converter.domain.util.toPto
import me.project.ports.model.UserPto

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