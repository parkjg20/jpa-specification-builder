package org.parkjg20.specificationbuilder.sample.application.board.comment.usecase.impl

import org.hibernate.exception.ConstraintViolationException
import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentAddDto
import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentDto
import org.parkjg20.specificationbuilder.application.board.comment.usecase.BoardCommentAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostStatusNotCommentableException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.exception.DuplicatedEntityException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardCommentAdd(
    private val repository: BoardCommentEntityRepositoryInterface,
    private val boardPostRepository: BoardPostEntityRepositoryInterface
) : BoardCommentAddUseCase {

    override fun execute(addDto: BoardCommentAddDto, actor: BoardActor): BoardCommentDto {

        return try {
            closure(addDto, actor)
        } catch (e: DataIntegrityViolationException) {
            if (ConstraintViolationException::class.java.isAssignableFrom(e.cause!!::class.java)
                && (e.cause as ConstraintViolationException).constraintName.contains("UNIQUE")) {

                throw DuplicatedEntityException()
            }

            throw e
        }
    }

    @Retryable(maxAttempts = 3, exclude = [ DataIntegrityViolationException::class ])
    @Transactional
    fun closure(addDto: BoardCommentAddDto, actor: BoardActor): BoardCommentDto {

        val post = boardPostRepository.findByIdOrFail(addDto.postId)
        if (!post.status!!.isCommentable()) {
            throw BoardPostStatusNotCommentableException()
        }

        val parent: BoardComment? = if (addDto.commentId == null) {
            null
        } else {
            repository.findById(addDto.commentId)
        }

        val comment = BoardComment.create(actor.user, addDto, post, parent)
        repository.save(comment)
        return BoardCommentDto.of(comment)
    }

}