package org.parkjg20.specificationbuilder.application.board.post.service.impl

import org.parkjg20.specificationbuilder.application.board.post.service.BoardServiceInterface
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardChannelRepositoryInterface
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class BoardService(
    private val boardRepository: BoardChannelRepositoryInterface
) : BoardServiceInterface {

    override fun uploadPost(board: BoardChannel): BoardChannel {
        board.lastUploadedAt = Instant.now()

        return boardRepository.save(board)
    }
}