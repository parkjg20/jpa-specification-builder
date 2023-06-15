package org.parkjg20.specificationbuilder.application.board.channel.usecase

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto
import org.parkjg20.specificationbuilder.application.board.usecase.GetManyUsingCursorUseCase

interface BoardChannelGetManyUsingCursorUseCase : GetManyUsingCursorUseCase<BoardChannelFilterDto, BoardChannelDto> {

}