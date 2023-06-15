package org.parkjg20.specificationbuilder.sample.application.common.dto

data class CursorPaginatedResultDto<T>(
    val cursor: String?,
    val list: List<T>,
    val total: Int
) {
}