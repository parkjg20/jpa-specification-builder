package org.parkjg20.specificationbuilder.application.board.channel.usecase

import com.neoguri.neogurinest.api.application.board.channel.dto.BoardChannelDto
import com.neoguri.neogurinest.api.application.board.channel.dto.BoardChannelFilterDto
import com.neoguri.neogurinest.api.application.board.usecase.GetManyUsingCursorUseCase

interface BoardChannelGetManyUsingCursorUseCase : GetManyUsingCursorUseCase<BoardChannelFilterDto, BoardChannelDto> {

}