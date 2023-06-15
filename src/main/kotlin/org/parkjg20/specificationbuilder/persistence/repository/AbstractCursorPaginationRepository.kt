package org.parkjg20.specificationbuilder.persistence.repository

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.common.Cursor
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.persistence.specification.CursorSpecificationBuilder
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification

abstract class AbstractCursorPaginationRepository<T> : CursorPaginationInterface<T> {

    abstract fun buildNewCursor(cursors: List<Cursor>, sort: Sort, lastEntity: T): List<Cursor>

    override fun findBySpecificationUsingCursorImpl(cursorRequest: CursorPageRequest<T>): CursorPage<T> {
        val cursors = cursorRequest.cursors
        val cursorSpec = CursorSpecificationBuilder<T>().build(cursors)

        val filterSpec = cursorRequest.specification
        val specification = combineSpecifications(cursorSpec, filterSpec)
        val totalCount = countBySpecification(filterSpec)
        val cursorCount = countBySpecification(specification)

        return if (cursorCount < 1) {
            CursorPage.emptyPage(totalCount)
        } else {

            val page = findBySpecificationUsingPagination(specification, cursorRequest.page)

            val newCursor = buildNewCursor(cursors, cursorRequest.page.sort, page.last())
            CursorPage(newCursor, page.toList(), totalCount)
        }
    }

    private fun combineSpecifications(vararg specs: Specification<T>?): Specification<T>? {
        return if (specs.filterNotNull().isEmpty()) {
            null
        } else {
            specs.filterNotNull().reduce { it, acc -> acc.and(it) }
        }
    }

}
