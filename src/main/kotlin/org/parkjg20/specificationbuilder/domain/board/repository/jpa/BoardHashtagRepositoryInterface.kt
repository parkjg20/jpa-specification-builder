package org.parkjg20.specificationbuilder.domain.board.repository.jpa

import org.parkjg20.specificationbuilder.domain.board.entity.BoardHashtag
import org.springframework.data.jpa.repository.JpaRepository

interface BoardHashtagRepositoryInterface : JpaRepository<BoardHashtag, String> {
    fun findByNameIn(names: List<String>): List<BoardHashtag>
}