package org.parkjg20.specificationbuilder.application.board.comment.usecase

import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentDto
import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentUpdateDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface BoardCommentUpdateUseCase {

    fun execute(commentId: String, updateDto: BoardCommentUpdateDto, actor: BoardActor): BoardCommentDto
}