package org.parkjg20.specificationbuilder.application.board.channel.usecase.impl

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.BoardChannelGetManyUseCase
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.persistence.specification.board.channel.BoardChannelSpecification
import org.springframework.stereotype.Service

@Service
class BoardChannelGetMany(
    val channelRepository: BoardChannelEntityRepositoryInterface
) : BoardChannelGetManyUseCase {

    override fun execute(filterDto: BoardChannelFilterDto): List<BoardChannelDto> {

        val specification = BoardChannelSpecification.of(filterDto)
        val channels: List<BoardChannel> = channelRepository.findBySpecification(specification, 100)

        return channels.map {
            BoardChannelDto.of(it)
        }
    }
}