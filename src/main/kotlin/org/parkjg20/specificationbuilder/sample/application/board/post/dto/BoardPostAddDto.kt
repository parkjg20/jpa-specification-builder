package org.parkjg20.specificationbuilder.sample.application.board.post.dto

data class BoardPostAddDto(
    val channelId: String,
    val title: String,
    val content: String,
    val hashTags: List<String>
) {}