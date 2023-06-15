package org.parkjg20.specificationbuilder.persistence.repository.board

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostBookmark
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostBookmarkId
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostBookmarkEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardPostBookmarkRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class BoardPostBookmarkEntityRepository(
    val repository: BoardPostBookmarkRepositoryInterface
) : BoardPostBookmarkEntityRepositoryInterface {

    override fun save(entity: BoardPostBookmark): BoardPostBookmark {
        return repository.save(entity)
    }

    override fun findById(id: BoardPostBookmarkId): BoardPostBookmark? {
        return repository.findById(id).orElse(null)
    }
}
