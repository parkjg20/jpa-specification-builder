package org.parkjg20.specificationbuilder.application.board.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.usecase.BoardPostGetUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class BoardPostGet(
    val boardPostRepository: BoardPostEntityRepositoryInterface
): BoardPostGetUseCase {

    @Throws(BoardPostNotFoundException::class)
    override fun execute(postId: String, actor: BoardActor?): BoardPostDto {

        return try {
            val post = boardPostRepository.findByIdOrFail(postId)

            // TODO: Actor 정보에 따라 Action을 조회하도록 구현
            BoardPostDto.of(post)
        } catch (e: EntityNotFoundException) {
            throw BoardPostNotFoundException()
        }
    }
}
