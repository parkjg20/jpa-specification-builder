package org.parkjg20.specificationbuilder.persistence.repository.board

import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentReport
import org.parkjg20.specificationbuilder.domain.board.repository.BoardCommentReportEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardCommentReportRepositoryInterface
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class BoardCommentReportEntityRepository(
    val repository: BoardCommentReportRepositoryInterface
) : BoardCommentReportEntityRepositoryInterface {

    override fun save(entity: BoardCommentReport): BoardCommentReport {
        return repository.save(entity)
    }

    override fun findById(id: String): BoardCommentReport? {
        return repository.findById(id).orElse(null)
    }

    override fun findByIdOrFail(id: String): BoardCommentReport {
        return findById(id) ?: throw EntityNotFoundException()
    }
}
