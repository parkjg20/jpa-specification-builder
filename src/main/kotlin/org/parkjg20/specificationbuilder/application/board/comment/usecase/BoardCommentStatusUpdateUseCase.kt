package org.parkjg20.specificationbuilder.application.board.comment.usecase

import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.enum.BoardCommentStatus

interface BoardCommentStatusUpdateUseCase {

    fun execute(commentId: String, status: BoardCommentStatus, actor: BoardActor): Boolean
}