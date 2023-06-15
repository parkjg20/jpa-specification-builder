package org.parkjg20.specificationbuilder.sample.application.board.post.usecase

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostUpdateDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostCannotUpdateException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException

interface BoardPostUpdateUseCase {

    @Throws(BoardPostNotFoundException::class, BoardPostCannotUpdateException::class)
    fun execute(updateDto: BoardPostUpdateDto, actor: BoardActor): BoardPostDto
}