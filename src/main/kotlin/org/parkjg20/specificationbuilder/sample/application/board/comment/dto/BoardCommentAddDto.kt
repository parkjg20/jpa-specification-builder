package org.parkjg20.specificationbuilder.sample.application.board.comment.dto

data class BoardCommentAddDto(
    val postId: String,
    val content: String,
    val commentId: String?,
) {}