package org.parkjg20.specificationbuilder.sample.application.board.channel.usecase.impl

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.BoardChannelGetManyUsingCursorUseCase
import org.parkjg20.specificationbuilder.application.board.usecase.AbstractGetManyUsingCursor
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginationDto
import org.parkjg20.specificationbuilder.application.common.dto.OrderRequestDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.persistence.specification.board.channel.BoardChannelSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BoardChannelGetManyUsingCursor(
    private val repository: BoardChannelEntityRepositoryInterface
) : AbstractGetManyUsingCursor(), BoardChannelGetManyUsingCursorUseCase {

    override fun execute(
        filter: BoardChannelFilterDto,
        pagination: CursorPaginationDto,
        orders: List<OrderRequestDto>,
        actor: BoardActor?
    ): CursorPaginatedResultDto<BoardChannelDto> {

        val filterSpec = BoardChannelSpecification.of(filter)

        val sort: Sort = makeSort(orders, pagination.cursors, Sort.by(Sort.Order(Sort.Direction.DESC, "createdAt")))

        val boardPosts = repository.findBySpecificationUsingCursor(
            CursorPageRequest(pagination.cursors, filterSpec, PageRequest.of(0, pagination.size, sort))
        )

        return CursorPaginatedResultDto(
            buildCursorString(boardPosts.cursors),
            boardPosts.list.map { BoardChannelDto.of(it) },
            boardPosts.total
        )
    }

}