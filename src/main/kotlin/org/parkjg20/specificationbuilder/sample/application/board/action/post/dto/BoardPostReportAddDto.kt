package org.parkjg20.specificationbuilder.sample.application.board.action.post.dto

import org.parkjg20.specificationbuilder.domain.board.enum.BoardPostReportType

data class BoardPostReportAddDto(
    val postId: String,
    val type: BoardPostReportType,
    val content: String
) {
}