package org.parkjg20.specificationbuilder.sample.repository.board

import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLike
import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLikeId
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentLikeEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardCommentLikeRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class BoardCommentLikeEntityRepository(
    val repository: BoardCommentLikeRepositoryInterface
) : BoardCommentLikeEntityRepositoryInterface {

    override fun save(entity: BoardCommentLike): BoardCommentLike {
        return repository.save(entity)
    }

    override fun findById(id: BoardCommentLikeId): BoardCommentLike? {
        return repository.findById(id).orElse(null)
    }
}
