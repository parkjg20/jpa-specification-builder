package org.parkjg20.specificationbuilder.application.board.post.usecase

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostAddDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotAvailableStatusException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException

interface BoardPostAddUseCase {

    @Throws(BoardChannelNotFoundException::class, BoardChannelNotAvailableStatusException::class)
    fun execute(addDto: BoardPostAddDto, actor: BoardActor?): BoardPostDto
}