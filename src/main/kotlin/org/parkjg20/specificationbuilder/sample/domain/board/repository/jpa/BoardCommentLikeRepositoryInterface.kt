package org.parkjg20.specificationbuilder.sample.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLike
import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLikeId
import org.springframework.data.jpa.repository.JpaRepository

interface BoardCommentLikeRepositoryInterface : JpaRepository<BoardCommentLike, BoardCommentLikeId> {

}