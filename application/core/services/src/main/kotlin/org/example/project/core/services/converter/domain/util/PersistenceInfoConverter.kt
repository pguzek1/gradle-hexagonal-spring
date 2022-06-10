package org.example.project.core.services.converter.domain.util

import org.example.project.core.domain.model.util.PersistenceInfo
import org.example.project.ports.model.util.PersistenceInfoPto

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