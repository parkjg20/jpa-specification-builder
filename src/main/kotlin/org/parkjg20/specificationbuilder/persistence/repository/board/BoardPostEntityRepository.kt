package org.parkjg20.specificationbuilder.persistence.repository.board

import org.parkjg20.specificationbuilder.application.common.dto.CursorPage
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardPostHashtagRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardPostRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.Cursor
import org.parkjg20.specificationbuilder.domain.common.CursorBuilder
import org.parkjg20.specificationbuilder.domain.common.CursorPageRequest
import org.parkjg20.specificationbuilder.persistence.repository.AbstractCursorPaginationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository

@Repository
class BoardPostEntityRepository(
    val repository: BoardPostRepositoryInterface,
    val hashTagRepository: BoardPostHashtagRepositoryInterface
) : AbstractCursorPaginationRepository<BoardPost>(), BoardPostEntityRepositoryInterface {

    override fun save(entity: BoardPost): BoardPost {
        if (!entity.hashTags.isEmpty()) {
            hashTagRepository.saveAll(entity.hashTags)
        }

        repository.save(entity)

        return entity
    }

    override fun findById(id: String): BoardPost? {
        return repository.findById(id).orElse(null)
    }

    override fun findByIdOrFail(id: String): BoardPost {
        return findById(id) ?: throw BoardPostNotFoundException()
    }

    override fun findBySpecification(
        specification: Specification<BoardPost>,
        order: Sort.Order,
        limit: Int
    ): List<BoardPost> {

        return repository.findAll(specification, Sort.by(order))
    }

    override fun countBySpecification(specification: Specification<BoardPost>?): Int {

        return repository.count(specification).toInt()
    }

    override fun findBySpecificationUsingPagination(specification: Specification<BoardPost>?, page: Pageable): Page<BoardPost> {
        return repository.findAll(specification, page)
    }

    override fun findBySpecificationUsingCursor(cursorRequest: CursorPageRequest<BoardPost>): CursorPage<BoardPost> {
        return this.findBySpecificationUsingCursorImpl(cursorRequest)
    }

    override fun buildNewCursor(cursors: List<Cursor>, sort: Sort, lastEntity: BoardPost): List<Cursor> {
        return CursorBuilder(
            if (CursorBuilder(cursors).cursors.isEmpty()) {
                sort.map { Cursor(it, 0 as Comparable<Any>) }.toList()
            } else {
                cursors
            }
        ).fromEntity(lastEntity)
    }
}
