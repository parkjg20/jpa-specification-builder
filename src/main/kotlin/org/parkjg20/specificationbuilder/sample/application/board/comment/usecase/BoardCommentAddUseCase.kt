package org.parkjg20.specificationbuilder.sample.application.board.comment.usecase

import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentAddDto
import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface BoardCommentAddUseCase {

    fun execute(addDto: BoardCommentAddDto, actor: BoardActor): BoardCommentDto
}