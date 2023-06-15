package org.parkjg20.specificationbuilder.application.board.action.comment.usecase

import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentActionAlreadyExistException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentStatusNotActionableException

interface BoardCommentLikeAddUseCase {

    @Throws(
        BoardCommentNotFoundException::class,
        BoardCommentActionAlreadyExistException::class,
        BoardCommentStatusNotActionableException::class
    )
    fun execute(commentId: String, actor: BoardActor): Boolean
}