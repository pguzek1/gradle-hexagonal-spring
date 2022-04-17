package me.project.core.services.converter.query

import me.project.ports.driving.query.FindUserQueryDto

fun FindUserQueryDto.toDriven(): me.project.ports.driven.query.FindUserQueryDto {
    return me.project.ports.driven.query.FindUserQueryDto(
        id = id,
    )
}