package org.parkjg20.specificationbuilder.application.board.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostFilterDto
import org.parkjg20.specificationbuilder.application.board.post.usecase.BoardPostGetManyUsingCursorUseCase
import org.parkjg20.specificationbuilder.application.board.usecase.AbstractGetManyUsingCursor
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginationDto
import org.parkjg20.specificationbuilder.application.common.dto.OrderRequestDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.persistence.specification.board.post.BoardPostSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order
import org.springframework.stereotype.Service

@Service
class BoardPostGetManyUsingCursor(
    val boardPostRepository: BoardPostEntityRepositoryInterface
) : AbstractGetManyUsingCursor(), BoardPostGetManyUsingCursorUseCase {

    override fun execute(
        filter: BoardPostFilterDto,
        pagination: CursorPaginationDto,
        orders: List<OrderRequestDto>,
        actor: BoardActor?)
    : CursorPaginatedResultDto<BoardPostDto> {

        val filterSpec = BoardPostSpecification.of(filter)

        val sort: Sort = makeSort(orders, pagination.cursors, Sort.by(Order(Sort.Direction.DESC, "createdAt")))

        val boardPosts = boardPostRepository.findBySpecificationUsingCursor(
            CursorPageRequest(pagination.cursors, filterSpec, PageRequest.of(0, pagination.size, sort))
        )

        return CursorPaginatedResultDto(
            buildCursorString(boardPosts.cursors),
            boardPosts.list.map { BoardPostDto.of(it) },
            boardPosts.total
        )
    }

}