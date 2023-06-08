package pl.alfn.project.ports.model.user

import java.util.UUID

class FindUserQueryDriving(
    val id: UUID
)

class FindUserQueryDriven(
    val id: UUID
)

fun FindUserQueryDriving.toDriven(): FindUserQueryDriven =
    FindUserQueryDriven(
        id = id
    )