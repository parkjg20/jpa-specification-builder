package org.parkjg20.specificationbuilder.application.board.comment.usecase.impl

import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentDto
import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentFilterDto
import org.parkjg20.specificationbuilder.application.board.comment.usecase.BoardCommentGetManyUsingCursorUseCase
import org.parkjg20.specificationbuilder.application.board.usecase.AbstractGetManyUsingCursor
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginationDto
import org.parkjg20.specificationbuilder.application.common.dto.OrderRequestDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.persistence.specification.board.comment.BoardCommentSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BoardCommentGetManyUsingCursor(
    private val repository: BoardCommentEntityRepositoryInterface
) : AbstractGetManyUsingCursor(), BoardCommentGetManyUsingCursorUseCase {

    override fun execute(
        filter: BoardCommentFilterDto,
        pagination: CursorPaginationDto,
        orders: List<OrderRequestDto>,
        actor: BoardActor?
    ): CursorPaginatedResultDto<BoardCommentDto> {

        val filterSpec = BoardCommentSpecification.of(filter)

        val sort: Sort = makeSort(orders, pagination.cursors, Sort.by(Sort.Order(Sort.Direction.DESC, "createdAt")))

        val comments = repository.findBySpecificationUsingCursor(
            CursorPageRequest(pagination.cursors, filterSpec, PageRequest.of(0, pagination.size, sort))
        )

        return CursorPaginatedResultDto(
            buildCursorString(comments.cursors),
            comments.list.map { BoardCommentDto.of(it) },
            comments.total
        )
    }

}