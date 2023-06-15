package org.parkjg20.specificationbuilder.action.post

import org.junit.jupiter.api.Test
import org.parkjg20.specificationbuilder.application.board.action.post.dto.BoardPostReportAddDto
import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostLikeAddUseCase
import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostReportAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.enum.BoardPostReportType
import org.parkjg20.specificationbuilder.domain.user.repository.UserEntityRepositoryInterface
import org.springframework.boot.test.context.SpringBootTest
import java.nio.charset.Charset
import javax.annotation.Resource

@SpringBootTest
class BoardPostActionsTests {

    @Resource
    lateinit var boardPostLikeAdd: BoardPostLikeAddUseCase

    @Resource
    lateinit var boardPostReportAdd: BoardPostReportAddUseCase

    @Resource
    lateinit var userRepository: UserEntityRepositoryInterface

    @Test
    fun `게시글 좋아요`() {
        val postId = "e45cc5f2-42ad-4485-a045-cd2d1a306135"
        val user = userRepository.findByIdOrFail(1)
        val actor = BoardActor(user, emptyList())

        assert(boardPostLikeAdd.execute(postId, actor))

    }
    
    @Test
    fun `게시글 신고`() {
        val postId = "e45cc5f2-42ad-4485-a045-cd2d1a30613d"
        val type = BoardPostReportType.ETC
        val content = String("도배성 글인것 같아서 신고하겠습니다.".toByteArray(), Charset.defaultCharset())

        val user = userRepository.findByIdOrFail(1)
        val actor = BoardActor(user, emptyList())

        boardPostReportAdd.execute(
            BoardPostReportAddDto(
                postId,
                type,
                content
            ),
            actor
        )
    }
}