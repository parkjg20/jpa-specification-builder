package org.parkjg20.specificationbuilder.sample.domain.board.repository

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostBookmark
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostBookmarkId
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository

interface BoardPostBookmarkEntityRepositoryInterface: AggregateRootRepository<BoardPostBookmark, BoardPostBookmarkId> {

}