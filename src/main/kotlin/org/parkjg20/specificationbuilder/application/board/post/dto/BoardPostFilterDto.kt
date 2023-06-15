package org.parkjg20.specificationbuilder.application.board.post.dto

import org.parkjg20.specificationbuilder.domain.board.enum.BoardPostStatus
import org.parkjg20.specificationbuilder.persistence.specification.RangeInstant

data class BoardPostFilterDto(
    val channelId: String,
    val status: BoardPostStatus?,
    val title: String?,
    val content: String?,
    val createdAt: RangeInstant?,
    val updatedAt: RangeInstant?
) {}