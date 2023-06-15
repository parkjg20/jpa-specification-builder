package org.parkjg20.specificationbuilder.domain.board.repository

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLike
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostLikeId
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository

interface BoardPostLikeEntityRepositoryInterface: AggregateRootRepository<BoardPostLike, BoardPostLikeId> {

}