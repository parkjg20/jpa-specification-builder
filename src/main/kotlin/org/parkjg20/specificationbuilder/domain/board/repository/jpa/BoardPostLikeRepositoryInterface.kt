package org.parkjg20.specificationbuilder.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLike
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLikeId
import org.springframework.data.jpa.repository.JpaRepository

interface BoardPostLikeRepositoryInterface : JpaRepository<BoardPostLike, BoardPostLikeId> {

}