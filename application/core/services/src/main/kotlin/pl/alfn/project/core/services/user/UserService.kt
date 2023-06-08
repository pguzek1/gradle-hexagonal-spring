package pl.alfn.project.core.services.user

import java.time.Instant.now
import java.util.UUID
import mu.KotlinLogging.logger
import pl.alfn.project.core.domain.user.User
import pl.alfn.project.core.domain.util.PersistenceInfo
import pl.alfn.project.core.services.converter.toPto
import pl.alfn.project.core.services.util.Generators.uuid
import pl.alfn.project.ports.driven.user.IUserFindQueryDriven
import pl.alfn.project.ports.driven.user.IUserPersistCommandDriven
import pl.alfn.project.ports.driving.user.IUserFindQueryDriving
import pl.alfn.project.ports.driving.user.IUserPersistCommandDriving
import pl.alfn.project.ports.model.user.FindUserQueryDriving
import pl.alfn.project.ports.model.user.PersistUserCommandDriving
import pl.alfn.project.ports.model.user.UserPto
import pl.alfn.project.ports.model.user.toDriven

class UserService(
    private val findUserDriven: IUserFindQueryDriven,
    private val persistUserDriven: IUserPersistCommandDriven
) : IUserFindQueryDriving, IUserPersistCommandDriving {
    override suspend fun findUser(findUserQuery: FindUserQueryDriving): UserPto =
        findUserDriven.findUser(findUserQuery.toDriven())

    override suspend fun persistUser(userPersistCommand: PersistUserCommandDriving): UUID {
        val user = now().let { now ->
            User(
                firstName = userPersistCommand.firstName,
                lastName = userPersistCommand.lastName,
                persistenceInfo = PersistenceInfo(
                    id = uuid.generate(),
                    version = 0,
                    createdAt = now,
                    updatedAt = now
                )
            )
        }
        logger.trace { "Built user $user" }

        user.validate()

        return persistUserDriven.persistUser(user.toPto())
    }

    companion object {
        val logger = logger {}
    }
}