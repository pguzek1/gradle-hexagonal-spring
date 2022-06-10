package org.example.project.core.services.converter.query

import org.example.project.ports.driving.query.FindUserQueryDto as FindUserQueryDtoDriving
import org.example.project.ports.driven.query.FindUserQueryDto as FindUserQueryDtoDriven

fun FindUserQueryDtoDriving.toDriven(): FindUserQueryDtoDriven {
    return FindUserQueryDtoDriven(
        id = id,
    )
}