package org.parkjg20.specificationbuilder.sample.application.board.comment.usecase.impl

import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentDto
import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentFilterDto
import org.parkjg20.specificationbuilder.application.board.comment.usecase.BoardCommentGetManyUsingPaginationUseCase
import org.parkjg20.specificationbuilder.application.board.usecase.AbstractGetMany
import org.parkjg20.specificationbuilder.application.common.dto.PaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.PaginationDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentEntityRepositoryInterface
import org.parkjg20.specificationbuilder.persistence.specification.board.comment.BoardCommentSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class BoardCommentGetManyUsingPagination(
    private val repository: BoardCommentEntityRepositoryInterface
) : AbstractGetMany(), BoardCommentGetManyUsingPaginationUseCase {

    override fun execute(
        filter: BoardCommentFilterDto,
        pagination: PaginationDto,
        actor: BoardActor?
    ): PaginatedResultDto<BoardCommentDto> {

        val spec = BoardCommentSpecification.of(filter)

        val sort = makeSort(pagination.orders)
        val page = PageRequest.of(pagination.page, pagination.size, sort)
        val result = repository.findBySpecificationUsingPagination(spec, page)

        val list = result.content.map { e: BoardComment ->
            BoardCommentDto.of(e)
        }
        return PaginatedResultDto.of(pagination, list, result)
    }

}