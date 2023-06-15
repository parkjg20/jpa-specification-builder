package org.parkjg20.specificationbuilder.application.board.channel.usecase.impl

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.BoardChannelGetUseCase
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.springframework.stereotype.Service

@Service
class BoardChannelGet(
    val channelRepository: BoardChannelEntityRepositoryInterface
) : BoardChannelGetUseCase {

    override fun execute(channelId: String): BoardChannelDto {

        val channel: BoardChannel = channelRepository.findByIdOrFail(channelId)

        return BoardChannelDto.of(channel)
    }
}