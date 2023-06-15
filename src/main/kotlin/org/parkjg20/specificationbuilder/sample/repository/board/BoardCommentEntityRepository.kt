package org.parkjg20.specificationbuilder.sample.repository.board

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentNotFoundException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardCommentRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.Cursor
import org.parkjg20.specificationbuilder.domain.common.CursorBuilder
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.persistence.specification.CursorSpecificationBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository

@Repository
class BoardCommentEntityRepository(
    val repository: BoardCommentRepositoryInterface
) : BoardCommentEntityRepositoryInterface {

    override fun save(entity: BoardComment): BoardComment {
        repository.save(entity)

        return entity
    }

    override fun findById(id: String): BoardComment? {
        return repository.findById(id).orElse(null)
    }

    override fun findByIdOrFail(id: String): BoardComment {
        return findById(id) ?: throw BoardCommentNotFoundException()
    }

    override fun findBySpecification(
        specification: Specification<BoardComment>,
        order: Sort,
        limit: Int
    ): List<BoardComment> {

        return repository.findAll(specification, order)
    }

    override fun countBySpecification(specification: Specification<BoardComment>?): Int {

        return repository.count(specification).toInt()
    }

    override fun findBySpecificationUsingCursor(
        cursorRequest: CursorPageRequest<BoardComment>
    ): CursorPage<BoardComment> {

        val cursors = cursorRequest.cursors
        val cursorSpec = CursorSpecificationBuilder<BoardComment>().build(cursors)

        val filterSpec = cursorRequest.specification
        val specification = combineSpecifications(cursorSpec, filterSpec)
        val totalCount = countBySpecification(filterSpec)
        val cursorCount = countBySpecification(specification)

        return if (cursorCount < 1) {
            CursorPage.emptyPage(totalCount)
        } else {

            val page = repository.findAll(specification, cursorRequest.page)

            val newCursor =
                CursorBuilder(
                    if (CursorBuilder(cursors).cursors.isEmpty()) {
                        cursorRequest.page.sort.map { Cursor(it, 0 as Comparable<Any>) }.toList()
                    } else {
                        cursors
                    }
                ).fromEntity(page.last())

            CursorPage(newCursor, page.toList(), totalCount)
        }
    }

    override fun findBySpecificationUsingPagination(
        specification: Specification<BoardComment>?,
        pageRequest: PageRequest
    ): Page<BoardComment> {
        return repository.findAll(specification, pageRequest)
    }


    private fun combineSpecifications(vararg specs: Specification<BoardComment>?): Specification<BoardComment>? {
        return if (specs.filterNotNull().isEmpty()) {
            null
        } else {
            specs.filterNotNull().reduce { it, acc -> acc.and(it) }
        }
    }
}
