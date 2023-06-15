package org.parkjg20.specificationbuilder.sample.application.board.action.comment.usecase.impl

import org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto
import org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportDto
import org.parkjg20.specificationbuilder.application.board.action.comment.usecase.BoardCommentReportAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentReport
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostStatusNotActionableException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentReportEntityRepositoryInterface
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardCommentReportAdd(
    val repository: BoardCommentReportEntityRepositoryInterface,
    val commentRepository: BoardCommentEntityRepositoryInterface
) : BoardCommentReportAddUseCase {

    override fun execute(addDto: org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto, actor: BoardActor): org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportDto {
        return executeImpl(addDto, actor)
    }

    @Retryable(maxAttempts = 3, exclude = [ BoardPostStatusNotActionableException::class ])
    @Transactional
    protected fun executeImpl(addDto: org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto, actor: BoardActor): org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportDto {

        val comment = commentRepository.findByIdOrFail(addDto.commentId)
        if (!comment.status!!.isActionable()) {
            throw BoardPostStatusNotActionableException()
        }

        val entity = BoardCommentReport.create(addDto, comment, actor.user)

        repository.save(entity)

        return org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportDto.of(entity)
    }
}