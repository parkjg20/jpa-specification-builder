package org.parkjg20.specificationbuilder.sample.domain.board.repository

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface BoardChannelEntityRepositoryInterface: AggregateRootRepository<BoardChannel, String> {

    @Throws(BoardChannelNotFoundException::class)
    fun findByIdOrFail(id: String): BoardChannel

    fun findBySpecification(specification: Specification<BoardChannel>?, limit: Int?): List<BoardChannel>

    fun findBySpecificationUsingCursor(
        cursorRequest: CursorPageRequest<BoardChannel>
    ): CursorPage<BoardChannel>

    fun findBySpecificationUsingPagination(
        specification: Specification<BoardChannel>?,
        page: Pageable
     ): Page<BoardChannel>

}