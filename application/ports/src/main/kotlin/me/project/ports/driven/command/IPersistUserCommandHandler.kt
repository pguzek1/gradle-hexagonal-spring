package me.project.ports.driven.command

import java.util.UUID
import me.project.ports.model.UserPto

interface IPersistUserCommandHandler {
    fun persistUser(user: UserPto): UUID
}