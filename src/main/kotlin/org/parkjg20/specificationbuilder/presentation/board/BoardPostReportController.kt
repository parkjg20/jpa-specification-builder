package org.parkjg20.specificationbuilder.presentation.board

import org.parkjg20.specificationbuilder.application.aspect.BoardContext
import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportAddDto
import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportDto
import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostReportAddUseCase
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotAvailableStatusException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException
import org.parkjg20.specificationbuilder.presentation.BaseController
import org.parkjg20.specificationbuilder.presentation.exception.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/board/post-reports")
class BoardPostReportController(
    val add: BoardPostReportAddUseCase
) : BaseController() {

    @PostMapping
    fun add(
        @RequestBody addDto: BoardPostReportAddDto,
    ): ResponseEntity<BoardPostReportDto> {
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        return try {
            val payload = add.execute(addDto, boardContext.actor!!)
            ResponseEntity
                .created(URI("/api/board/post-reports/${payload.reportId}"))
                .body(payload)
        } catch (e: BoardChannelNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw ForbiddenException(e.message!!)
        }
    }
}