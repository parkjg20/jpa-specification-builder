package org.parkjg20.specificationbuilder.sample.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentReport
import org.springframework.data.jpa.repository.JpaRepository

interface BoardCommentReportRepositoryInterface : JpaRepository<BoardCommentReport, String> {

}