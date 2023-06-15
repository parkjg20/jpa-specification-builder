package org.parkjg20.specificationbuilder.sample.application.board.channel.usecase

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto

interface BoardChannelGetManyUseCase {

    fun execute(filterDto: BoardChannelFilterDto): List<BoardChannelDto>
}