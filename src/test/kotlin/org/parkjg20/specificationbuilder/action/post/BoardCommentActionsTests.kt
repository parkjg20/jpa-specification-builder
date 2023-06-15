package org.parkjg20.specificationbuilder.action.post

import org.junit.jupiter.api.Test
import org.parkjg20.specificationbuilder.application.board.action.comment.usecase.BoardCommentLikeAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.user.repository.UserEntityRepositoryInterface
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.Resource

@SpringBootTest
class BoardCommentActionsTests {

    @Resource
    lateinit var userRepository: UserEntityRepositoryInterface

    @Resource
    lateinit var boardCommentLikeAdd: BoardCommentLikeAddUseCase

    @Test
    fun `댓글 좋아요`() {
        val commentId = "70ce23c8-68d7-414e-9956-b85a5b60aa24"
        val user = userRepository.findByIdOrFail(1)
        val actor = BoardActor(user, emptyList())

        assert(boardCommentLikeAdd.execute(commentId, actor))

    }
}