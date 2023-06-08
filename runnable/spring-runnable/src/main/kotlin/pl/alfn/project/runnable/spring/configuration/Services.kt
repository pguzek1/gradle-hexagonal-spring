package pl.alfn.project.runnable.spring.configuration

import java.time.Instant
import java.util.UUID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.alfn.project.core.services.user.UserService
import pl.alfn.project.ports.driven.user.IUserFindQueryDriven
import pl.alfn.project.ports.driven.user.IUserPersistCommandDriven
import pl.alfn.project.ports.model.user.FindUserQueryDriven
import pl.alfn.project.ports.model.user.UserPto
import pl.alfn.project.ports.model.util.PersistenceInfoPto

@Configuration
class Services {
    @Bean
    fun findUserDriven(): IUserFindQueryDriven =
        object : IUserFindQueryDriven {
            override suspend fun findUser(findUserQuery: FindUserQueryDriven): UserPto {
                return UserPto(
                    "asd",
                    "asd",
                    PersistenceInfoPto(
                        UUID.randomUUID(),
                        0,
                        Instant.now(),
                        Instant.now()
                    )
                )
            }
        }

    @Bean
    fun persistUserDriven(): IUserPersistCommandDriven =
        object : IUserPersistCommandDriven {
            override suspend fun persistUser(user: UserPto): UUID {
                return UUID.randomUUID()
            }
        }

    @Bean
    fun userService(
        findUserDriven: IUserFindQueryDriven,
        persistUserDriven: IUserPersistCommandDriven,
    ): UserService =
        UserService(findUserDriven, persistUserDriven)
}