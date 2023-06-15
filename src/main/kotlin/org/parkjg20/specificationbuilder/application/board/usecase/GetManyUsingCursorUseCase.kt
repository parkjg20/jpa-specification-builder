package org.parkjg20.specificationbuilder.application.board.usecase

import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.CursorPaginationDto
import org.parkjg20.specificationbuilder.application.common.dto.OrderRequestDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface GetManyUsingCursorUseCase<F, T> {

    fun execute(
        filter: F,
        pagination: CursorPaginationDto,
        orders: List<OrderRequestDto>,
        actor: BoardActor?
    ): CursorPaginatedResultDto<T>
}