package pl.alfn.project.core.services.converter

import pl.alfn.project.core.domain.user.User
import pl.alfn.project.core.services.converter.util.toDomain
import pl.alfn.project.core.services.converter.util.toPto
import pl.alfn.project.ports.model.user.UserPto

fun User.toPto(): UserPto =
    UserPto(
        firstName = firstName,
        lastName = lastName,
        persistenceInfo = persistenceInfo.toPto()
    )

fun UserPto.toDomain(): User =
    User(
        firstName = firstName,
        lastName = lastName,
        persistenceInfo = persistenceInfo.toDomain(),
    )
