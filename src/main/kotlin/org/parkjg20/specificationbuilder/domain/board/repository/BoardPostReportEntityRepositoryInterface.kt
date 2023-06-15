package org.parkjg20.specificationbuilder.domain.board.repository

import com.neoguri.neogurinest.api.domain.board.entity.BoardPostReport
import com.neoguri.neogurinest.api.domain.common.repository.AggregateRootRepository

interface BoardPostReportEntityRepositoryInterface: AggregateRootRepository<BoardPostReport, String> {

    fun findByIdOrFail(id: String): BoardPostReport
}