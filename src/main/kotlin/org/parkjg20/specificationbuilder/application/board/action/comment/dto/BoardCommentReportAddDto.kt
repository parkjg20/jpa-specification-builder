package org.parkjg20.specificationbuilder.application.board.action.comment.dto

import org.parkjg20.specificationbuilder.domain.board.enum.BoardCommentReportType

data class BoardCommentReportAddDto(
    val commentId: String,
    val type: BoardCommentReportType,
    val content: String
) {
}