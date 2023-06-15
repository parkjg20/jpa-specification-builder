package org.parkjg20.specificationbuilder.application.board.channel.usecase

import com.neoguri.neogurinest.api.application.board.channel.dto.BoardChannelDto

interface BoardChannelGetUseCase {

    fun execute(channelId: String): BoardChannelDto
}