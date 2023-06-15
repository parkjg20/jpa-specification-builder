package org.parkjg20.specificationbuilder.application.board.comment.usecase

import com.neoguri.neogurinest.api.application.board.comment.dto.BoardCommentDto
import com.neoguri.neogurinest.api.application.board.comment.dto.BoardCommentUpdateDto
import com.neoguri.neogurinest.api.domain.board.bean.BoardActor

interface BoardCommentUpdateUseCase {

    fun execute(commentId: String, updateDto: BoardCommentUpdateDto, actor: BoardActor): BoardCommentDto
}