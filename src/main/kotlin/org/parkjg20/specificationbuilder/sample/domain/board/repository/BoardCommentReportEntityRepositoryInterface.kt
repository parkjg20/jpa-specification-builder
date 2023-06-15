package org.parkjg20.specificationbuilder.sample.domain.board.repository

import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentReport
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository

interface BoardCommentReportEntityRepositoryInterface: AggregateRootRepository<BoardCommentReport, String> {
    fun findByIdOrFail(id: String): BoardCommentReport
}