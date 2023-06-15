package org.parkjg20.specificationbuilder.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BoardCommentRepositoryInterface : JpaRepository<BoardComment, String>, JpaSpecificationExecutor<BoardComment> {
}