package org.parkjg20.specificationbuilder.sample.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostHashtag
import org.springframework.data.jpa.repository.JpaRepository

interface BoardPostHashtagRepositoryInterface : JpaRepository<BoardPostHashtag, String> {
}