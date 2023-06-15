package org.parkjg20.specificationbuilder.application.board.action.comment.usecase

import org.parkjg20.specificationbuilder.application.board.action.comment.dto.BoardCommentReportAddDto
import org.parkjg20.specificationbuilder.application.board.action.comment.dto.BoardCommentReportDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface BoardCommentReportAddUseCase {

    fun execute(addDto: BoardCommentReportAddDto, actor: BoardActor): BoardCommentReportDto
}