package org.parkjg20.specificationbuilder.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostReport
import org.springframework.data.jpa.repository.JpaRepository

interface BoardPostReportRepositoryInterface : JpaRepository<BoardPostReport, String> {

}