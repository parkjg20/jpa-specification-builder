package org.parkjg20.specificationbuilder.application.board.comment.dto

import org.parkjg20.specificationbuilder.domain.board.enum.BoardCommentStatus
import org.parkjg20.specificationbuilder.persistence.specification.RangeInstant

data class BoardCommentFilterDto(
    val nestId: String?,
    val channelId: String?,
    val postId: String?,
    val userId: String?,
    val parentId: String?,
    val status: List<BoardCommentStatus>?,
    val createdAt: RangeInstant?,
    val updatedAt: RangeInstant?
) {}