package org.example.project.ports.driven.command

import java.util.UUID
import org.example.project.ports.model.UserPto

interface IPersistUserCommandHandler {
    fun persistUser(user: UserPto): UUID
}