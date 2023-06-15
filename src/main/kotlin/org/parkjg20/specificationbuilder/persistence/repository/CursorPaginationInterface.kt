package org.parkjg20.specificationbuilder.persistence.repository

import com.neoguri.neogurinest.api.application.common.dto.CursorPage
import com.neoguri.neogurinest.api.domain.common.CursorPageRequest

interface CursorPaginationInterface<T>: PaginationInterface<T> {

    fun findBySpecificationUsingCursorImpl(cursorRequest: CursorPageRequest<T>): CursorPage<T>
}
