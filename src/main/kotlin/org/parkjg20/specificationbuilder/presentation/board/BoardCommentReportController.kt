package org.parkjg20.specificationbuilder.presentation.board

import org.parkjg20.specificationbuilder.application.aspect.BoardContext
import org.parkjg20.specificationbuilder.application.board.action.comment.dto.BoardCommentReportAddDto
import org.parkjg20.specificationbuilder.application.board.action.comment.dto.BoardCommentReportDto
import org.parkjg20.specificationbuilder.application.board.action.comment.usecase.BoardCommentReportAddUseCase
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotAvailableStatusException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException
import org.parkjg20.specificationbuilder.presentation.BaseController
import org.parkjg20.specificationbuilder.presentation.exception.ForbiddenException
import org.parkjg20.specificationbuilder.presentation.exception.NotFoundException
import org.parkjg20.specificationbuilder.presentation.exception.UnauthorizedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/board/comment-reports")
class BoardCommentReportController(
    val add: BoardCommentReportAddUseCase
) : BaseController() {

    @PostMapping
    fun add(
        @RequestBody addDto: BoardCommentReportAddDto,
    ): ResponseEntity<BoardCommentReportDto> {
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        return try {
            val payload = add.execute(addDto, boardContext.actor!!)
            ResponseEntity
                .created(URI("/api/board/comment-reports/${payload.reportId}"))
                .body(payload)
        } catch (e: BoardChannelNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw ForbiddenException(e.message!!)
        }
    }
}