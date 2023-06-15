package org.parkjg20.specificationbuilder.application.board.action.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportAddDto
import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportDto
import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostReportAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostReport
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostStatusNotActionableException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostReportEntityRepositoryInterface
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardPostReportAdd(
    val repository: BoardPostReportEntityRepositoryInterface,
    val postRepository: BoardPostEntityRepositoryInterface
) : BoardPostReportAddUseCase {

    override fun execute(addDto: BoardPostReportAddDto, actor: BoardActor): BoardPostReportDto {
        return executeImpl(addDto, actor)
    }

    @Retryable(maxAttempts = 3, exclude = [ BoardPostStatusNotActionableException::class ])
    @Transactional
    protected fun executeImpl(addDto: BoardPostReportAddDto, actor: BoardActor): BoardPostReportDto {

        val post = postRepository.findByIdOrFail(addDto.postId)
        if (!post.status!!.isActionable()) {
            throw BoardPostStatusNotActionableException()
        }

        val entity = BoardPostReport.create(addDto, post, actor.user)

        repository.save(entity)

        return BoardPostReportDto.of(entity)
    }
}