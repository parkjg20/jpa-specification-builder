package org.parkjg20.specificationbuilder.sample.domain.board.repository

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification

interface BoardPostEntityRepositoryInterface: AggregateRootRepository<BoardPost, String> {

    fun findByIdOrFail(id: String): BoardPost

    fun findBySpecification(
        specification: Specification<BoardPost>,
        order: Sort.Order,
        limit: Int
    ): List<BoardPost>

    fun countBySpecification(specification: Specification<BoardPost>?): Int

    fun findBySpecificationUsingPagination(specification: Specification<BoardPost>?, page: Pageable): Page<BoardPost>

    fun findBySpecificationUsingCursor(cursorRequest: CursorPageRequest<BoardPost>): CursorPage<BoardPost>
}