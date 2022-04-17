package me.project.core.services.converter.domain.util

import me.project.core.domain.model.util.PersistenceInfo
import me.project.ports.model.util.PersistenceInfoPto

fun PersistenceInfo.toPto() : PersistenceInfoPto {
    return PersistenceInfoPto(
        id = id,
        version = version,
    )
}

fun PersistenceInfoPto.toDomain() : PersistenceInfo {
    return PersistenceInfo(
        id = id,
        version = version,
    )
}