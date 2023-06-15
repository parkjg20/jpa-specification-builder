package org.parkjg20.specificationbuilder.sample.application.board.post.usecase

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostStatusUpdateDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostCannotUpdateException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException

interface BoardPostStatusUpdateUseCase {

    @Throws(BoardPostNotFoundException::class, BoardPostCannotUpdateException::class)
    fun execute(postId: String, statusUpdateDto: BoardPostStatusUpdateDto, actor: BoardActor): BoardPostDto
}