package org.parkjg20.specificationbuilder.sample.application.board.post.dto

data class BoardPostUpdateDto(
    val postId: String,
    val boardId: String?,
    val nestId: Int?,
    val title: String?,
    val content: String?,
    val hashTags: List<String>
) {}