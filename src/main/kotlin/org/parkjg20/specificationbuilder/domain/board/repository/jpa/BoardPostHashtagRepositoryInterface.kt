package org.parkjg20.specificationbuilder.domain.board.repository.jpa

import com.neoguri.neogurinest.api.domain.board.entity.BoardPostHashtag
import org.springframework.data.jpa.repository.JpaRepository

interface BoardPostHashtagRepositoryInterface : JpaRepository<BoardPostHashtag, String> {
}