package org.parkjg20.specificationbuilder.sample.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostBookmark
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostBookmarkId
import org.springframework.data.jpa.repository.JpaRepository

interface BoardPostBookmarkRepositoryInterface : JpaRepository<BoardPostBookmark, BoardPostBookmarkId> {

}