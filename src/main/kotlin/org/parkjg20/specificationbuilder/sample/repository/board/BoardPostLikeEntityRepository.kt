package org.parkjg20.specificationbuilder.sample.repository.board

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLike
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLikeId
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostLikeEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardPostLikeRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class BoardPostLikeEntityRepository(
    val repository: BoardPostLikeRepositoryInterface
) : BoardPostLikeEntityRepositoryInterface {

    override fun save(entity: BoardPostLike): BoardPostLike {
        return repository.save(entity)
    }

    override fun findById(id: BoardPostLikeId): BoardPostLike? {
        return repository.findById(id).orElse(null)
    }
}
