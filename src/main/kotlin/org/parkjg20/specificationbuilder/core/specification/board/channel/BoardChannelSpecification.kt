package org.parkjg20.specificationbuilder.core.specification.board.channel

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.persistence.specification.ObjectSpecificationBuilder
import org.springframework.data.jpa.domain.Specification

class BoardChannelSpecification {

    companion object {
        fun of(filterDto: BoardChannelFilterDto): Specification<BoardChannel>? {
            return ObjectSpecificationBuilder<BoardChannelFilterDto, BoardChannel>(BoardChannelFilterDto::class.java)
                .build(filterDto)
        }
    }

}