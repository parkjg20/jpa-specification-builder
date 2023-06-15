package org.parkjg20.specificationbuilder.application.board.action.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostLikeAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLike
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostStatusNotActionableException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostLikeEntityRepositoryInterface
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardPostLikeAdd(
    val repository: BoardPostLikeEntityRepositoryInterface,
    val postRepository: BoardPostEntityRepositoryInterface
) : BoardPostLikeAddUseCase {

    @Throws(
        BoardPostNotFoundException::class,
        BoardPostStatusNotActionableException::class
    )
    override fun execute(postId: String, actor: BoardActor): Boolean {
        return executeImpl(postId, actor)
    }

    @Retryable(maxAttempts = 3, exclude = [ BoardPostStatusNotActionableException::class ])
    @Transactional
    protected fun executeImpl(postId: String, actor: BoardActor): Boolean {

        val post = postRepository.findByIdOrFail(postId)
        if (!post.status!!.isActionable()) {
            throw BoardPostStatusNotActionableException()
        }

        val entity = BoardPostLike.create(post, actor)

        repository.save(entity)
        return true
    }
}