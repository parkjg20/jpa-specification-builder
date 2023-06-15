package org.parkjg20.specificationbuilder.persistence.repository.board

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostReport
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostReportEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.jpa.BoardPostReportRepositoryInterface
import org.springframework.stereotype.Repository
import javax.persistence.EntityNotFoundException

@Repository
class BoardPostReportEntityRepository(
    val repository: BoardPostReportRepositoryInterface
) : BoardPostReportEntityRepositoryInterface {

    override fun save(entity: BoardPostReport): BoardPostReport {
        return repository.save(entity)
    }

    override fun findById(id: String): BoardPostReport? {
        return repository.findById(id).orElse(null)
    }

    override fun findByIdOrFail(id: String): BoardPostReport {
        return findById(id) ?: throw EntityNotFoundException()
    }
}
