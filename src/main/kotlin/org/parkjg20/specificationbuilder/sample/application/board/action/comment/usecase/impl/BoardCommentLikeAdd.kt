package org.parkjg20.specificationbuilder.sample.application.board.action.comment.usecase.impl

import org.parkjg20.specificationbuilder.application.board.action.comment.usecase.BoardCommentLikeAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLike
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentActionAlreadyExistException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardCommentStatusNotActionableException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostStatusNotActionableException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentLikeEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.exception.DuplicatedEntityException
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardCommentLikeAdd(
    val repository: BoardCommentLikeEntityRepositoryInterface,
    val commentRepository: BoardCommentEntityRepositoryInterface
) : BoardCommentLikeAddUseCase {

    @Throws(
        BoardCommentNotFoundException::class,
        BoardCommentActionAlreadyExistException::class,
        BoardCommentStatusNotActionableException::class
    )
    override fun execute(commentId: String, actor: BoardActor): Boolean {
        return executeImpl(commentId, actor)
    }

    @Retryable(maxAttempts = 3, exclude = [ DuplicatedEntityException::class, BoardPostStatusNotActionableException::class ])
    @Transactional
    protected fun executeImpl(commentId: String, actor: BoardActor): Boolean {

        val comment = commentRepository.findByIdOrFail(commentId)
        if (!comment.status!!.isActionable()) {
            throw BoardCommentStatusNotActionableException()
        }

        val entity = BoardCommentLike.create(comment, actor)

        repository.save(entity)
        return true
    }
}