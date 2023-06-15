package org.parkjg20.specificationbuilder.application.board.channel.usecase.impl

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.BoardChannelGetManyUsingPaginationUseCase
import org.parkjg20.specificationbuilder.application.board.usecase.AbstractGetMany
import org.parkjg20.specificationbuilder.application.common.dto.PaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.PaginationDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.persistence.specification.board.channel.BoardChannelSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class BoardChannelGetManyUsingPagination(
    val repository: BoardChannelEntityRepositoryInterface
) : AbstractGetMany(), BoardChannelGetManyUsingPaginationUseCase {

    override fun execute(
        filter: BoardChannelFilterDto,
        pagination: PaginationDto,
        actor: BoardActor?
    ): PaginatedResultDto<BoardChannelDto> {

        val filterSpec = BoardChannelSpecification.of(filter)

        val sort = makeSort(pagination.orders)
        val pageRequest = PageRequest.of(pagination.page, pagination.size, sort)
        val page = repository.findBySpecificationUsingPagination(filterSpec, pageRequest)

        return PaginatedResultDto.of(pagination, page.content.map { BoardChannelDto.of(it) }, page)
    }
}