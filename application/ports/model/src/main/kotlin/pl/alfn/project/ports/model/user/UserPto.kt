package pl.alfn.project.ports.model.user

import pl.alfn.project.ports.model.util.PersistenceInfoPto

data class UserPto(
    val firstName: String,
    val lastName: String,
    val persistenceInfo: PersistenceInfoPto
)