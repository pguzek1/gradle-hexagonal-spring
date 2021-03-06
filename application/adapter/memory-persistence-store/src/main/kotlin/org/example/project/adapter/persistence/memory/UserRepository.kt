package org.example.project.adapter.persistence.memory

import java.util.UUID
import org.example.project.ports.driven.command.IPersistUserCommandHandler
import org.example.project.ports.driven.query.FindUserQueryDto
import org.example.project.ports.driven.query.IFindUserQueryHandler
import org.example.project.ports.model.UserPto
import org.example.project.ports.model.util.PersistenceInfoPto

class UserRepository : IFindUserQueryHandler, IPersistUserCommandHandler {
    //TODO: should be UserEntity
    private val store = mutableListOf<UserPto>()

    override fun persistUser(user: UserPto): UUID {
        if (user.persistenceInfo != null) {
            throw RuntimeException("persistence info should be null")
        }

        val userEntity = user.copy(
            persistenceInfo = PersistenceInfoPto(
                id = UUID.randomUUID(),
                version = 0,
            )
        )

        store.add(userEntity)

        return userEntity.persistenceInfo!!.id
    }

    override fun findUser(findUserQueryDto: FindUserQueryDto): UserPto {
        return store.stream()
            .filter { user -> user.persistenceInfo!!.id == findUserQueryDto.id }
            .findFirst()
            .orElseThrow { RuntimeException("not found") }
    }

}