package org.parkjg20.specificationbuilder.domain.board.repository

import com.neoguri.neogurinest.api.domain.board.entity.BoardCommentLike
import com.neoguri.neogurinest.api.domain.board.entity.BoardCommentLikeId
import com.neoguri.neogurinest.api.domain.common.repository.AggregateRootRepository

interface BoardCommentLikeEntityRepositoryInterface: AggregateRootRepository<BoardCommentLike, BoardCommentLikeId> {

}