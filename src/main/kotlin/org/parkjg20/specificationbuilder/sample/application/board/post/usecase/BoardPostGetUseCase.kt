package org.parkjg20.specificationbuilder.sample.application.board.post.usecase

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException

interface BoardPostGetUseCase {

    @Throws(BoardPostNotFoundException::class)
    fun execute(postId: String, actor: BoardActor?): BoardPostDto
}