package org.parkjg20.specificationbuilder.sample.application.board.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostFilterDto
import org.parkjg20.specificationbuilder.application.board.post.usecase.BoardPostGetManyUsingPaginationUseCase
import org.parkjg20.specificationbuilder.application.board.usecase.AbstractGetMany
import org.parkjg20.specificationbuilder.application.common.dto.PaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.PaginationDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.parkjg20.specificationbuilder.persistence.specification.board.post.BoardPostSpecification
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class BoardPostGetManyUsingPagination(
    val repository: BoardPostEntityRepositoryInterface
) : AbstractGetMany(), BoardPostGetManyUsingPaginationUseCase {


    override fun execute(filter: BoardPostFilterDto, pagination: PaginationDto, actor: BoardActor?): PaginatedResultDto<BoardPostDto> {

        val filterSpec = BoardPostSpecification.of(filter)

        val sort = makeSort(pagination.orders)
        val pageRequest = PageRequest.of(pagination.page, pagination.size, sort)
        val page = repository.findBySpecificationUsingPagination(filterSpec, pageRequest)

        return PaginatedResultDto.of(pagination, page.content.map { BoardPostDto.of(it) }, page)
    }

}