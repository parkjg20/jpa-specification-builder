package org.parkjg20.specificationbuilder.sample.application.board.post.dto

import org.parkjg20.specificationbuilder.domain.board.enum.BoardPostStatus

data class BoardPostStatusUpdateDto(
    val status: BoardPostStatus
) {}