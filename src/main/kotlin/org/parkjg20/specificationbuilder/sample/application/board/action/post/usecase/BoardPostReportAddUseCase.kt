package org.parkjg20.specificationbuilder.sample.application.board.action.post.usecase

import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportAddDto
import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportDto
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor

interface BoardPostReportAddUseCase {

    fun execute(addDto: BoardPostReportAddDto, actor: BoardActor): BoardPostReportDto
}