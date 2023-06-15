package org.parkjg20.specificationbuilder.sample.application.board.action.comment.usecase

import org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto
import org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface BoardCommentReportAddUseCase {

    fun execute(addDto: org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto, actor: BoardActor): org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportDto
}