package org.parkjg20.specificationbuilder.domain.board.repository

import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLike
import org.parkjg20.specificationbuilder.domain.board.entity.BoardCommentLikeId
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository

interface BoardCommentLikeEntityRepositoryInterface: AggregateRootRepository<BoardCommentLike, BoardCommentLikeId> {

}