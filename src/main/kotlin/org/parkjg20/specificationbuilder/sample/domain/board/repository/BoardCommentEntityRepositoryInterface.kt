package org.parkjg20.specificationbuilder.sample.domain.board.repository

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification

interface BoardCommentEntityRepositoryInterface : AggregateRootRepository<BoardComment, String> {

    fun findByIdOrFail(id: String): BoardComment

    fun findBySpecification(
        specification: Specification<BoardComment>,
        order: Sort,
        limit: Int
    ): List<BoardComment>

    fun countBySpecification(specification: Specification<BoardComment>?): Int

    fun findBySpecificationUsingCursor(
        cursorRequest: CursorPageRequest<BoardComment>
    ): CursorPage<BoardComment>

    fun findBySpecificationUsingPagination(
        specification: Specification<BoardComment>?,
        pageRequest: PageRequest
    ): Page<BoardComment>

}