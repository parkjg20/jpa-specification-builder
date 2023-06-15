package org.parkjg20.specificationbuilder.application.common.dto

import org.parkjg20.specificationbuilder.domain.common.Cursor

data class CursorPaginationDto(val cursors: List<Cursor>, var size: Int = 50) {

    private val maximumSize = 50

    init {
        this.size = maximumSize.coerceAtMost(size)
    }
}
