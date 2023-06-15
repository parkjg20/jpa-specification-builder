package org.parkjg20.specificationbuilder.application.board.channel.dto

import org.parkjg20.specificationbuilder.domain.board.enum.BoardStatus

data class BoardStatusUpdateDto(
    val boardId: String,
    val status: BoardStatus
) {}