package org.parkjg20.specificationbuilder.application.nest.dto.request

import com.neoguri.neogurinest.api.domain.nest.enum.NestStatus

data class NestStatusUpdateDto(
    val nestId: Int,
    val status: NestStatus
) {}
