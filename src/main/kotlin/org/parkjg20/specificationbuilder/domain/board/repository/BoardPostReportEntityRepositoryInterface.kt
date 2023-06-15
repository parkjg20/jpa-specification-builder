package org.parkjg20.specificationbuilder.domain.board.repository

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostReport
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository

interface BoardPostReportEntityRepositoryInterface: AggregateRootRepository<BoardPostReport, String> {

    fun findByIdOrFail(id: String): BoardPostReport
}