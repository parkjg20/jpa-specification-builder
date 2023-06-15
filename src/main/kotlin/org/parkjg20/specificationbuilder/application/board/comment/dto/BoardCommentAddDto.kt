package org.parkjg20.specificationbuilder.application.board.comment.dto

data class BoardCommentAddDto(
    val postId: String,
    val content: String,
    val commentId: String?,
) {}