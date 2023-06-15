package org.parkjg20.specificationbuilder.sample.application.board.usecase

import org.parkjg20.specificationbuilder.application.common.dto.PaginatedResultDto
import org.parkjg20.specificationbuilder.application.common.dto.PaginationDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface GetManyUsingPaginationUseCase<F, T> {

    fun execute(
        filter: F,
        pagination: PaginationDto,
        actor: BoardActor?
    ): PaginatedResultDto<T>
}