package pl.alfn.project.core.services.converter.util

import pl.alfn.project.core.domain.util.PersistenceInfo
import pl.alfn.project.ports.model.util.PersistenceInfoPto

fun PersistenceInfo.toPto(): PersistenceInfoPto =
    PersistenceInfoPto(
        id = id,
        version = version,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

fun PersistenceInfoPto.toDomain(): PersistenceInfo =
    PersistenceInfo(
        id = id,
        version = version,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
