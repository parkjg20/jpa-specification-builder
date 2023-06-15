package org.parkjg20.specificationbuilder.sample.repository

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest

interface CursorPaginationInterface<T>: PaginationInterface<T> {

    fun findBySpecificationUsingCursorImpl(cursorRequest: CursorPageRequest<T>): CursorPage<T>
}
