package pl.alfn.project.ports.driven.user

import java.util.UUID
import pl.alfn.project.ports.model.user.FindUserQueryDriven
import pl.alfn.project.ports.model.user.UserPto

interface IUserFindQueryDriven {
    suspend fun findUser(findUserQuery: FindUserQueryDriven): UserPto
}

interface IUserPersistCommandDriven {
    suspend fun persistUser(user: UserPto): UUID
}