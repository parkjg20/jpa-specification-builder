package org.parkjg20.specificationbuilder.sample.application.board.action.post.usecase

import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostStatusNotActionableException

interface BoardPostBookmarkAddUseCase {

    @Throws(
        BoardPostNotFoundException::class,
        BoardPostStatusNotActionableException::class
    )
    fun execute(postId: String, actor: BoardActor): Boolean
}