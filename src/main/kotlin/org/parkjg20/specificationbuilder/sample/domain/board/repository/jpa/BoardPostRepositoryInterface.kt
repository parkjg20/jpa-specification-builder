package org.parkjg20.specificationbuilder.sample.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BoardPostRepositoryInterface : JpaRepository<BoardPost, String>, JpaSpecificationExecutor<BoardPost> {

}