package org.parkjg20.specificationbuilder.sample.application.common.dto

data class CursorPaginationRequestDto(
    val cursor: String?,
    val order: String?,
    val size: Int = 50
) {}
