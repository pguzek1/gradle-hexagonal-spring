package pl.alfn.project.ports.driving.user

import java.util.UUID
import pl.alfn.project.ports.model.user.FindUserQueryDriving
import pl.alfn.project.ports.model.user.PersistUserCommandDriving
import pl.alfn.project.ports.model.user.UserPto

interface IUserFindQueryDriving {
    suspend fun findUser(findUserQuery: FindUserQueryDriving): UserPto?
}

interface IUserPersistCommandDriving {
    suspend fun persistUser(userPersistCommand: PersistUserCommandDriving): UUID
}