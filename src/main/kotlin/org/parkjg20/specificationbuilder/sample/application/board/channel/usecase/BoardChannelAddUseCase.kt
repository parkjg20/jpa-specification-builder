package org.parkjg20.specificationbuilder.sample.application.board.channel.usecase

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardAddDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.domain.common.exception.DuplicatedEntityException

interface BoardChannelAddUseCase {

    @Throws(DuplicatedEntityException::class)
    fun execute(addDto: BoardAddDto): BoardChannelDto
}