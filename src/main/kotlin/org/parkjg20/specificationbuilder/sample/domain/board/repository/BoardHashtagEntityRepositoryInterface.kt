package org.parkjg20.specificationbuilder.sample.domain.board.repository

import org.parkjg20.specificationbuilder.domain.board.entity.BoardHashtag
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository

interface BoardHashtagEntityRepositoryInterface: AggregateRootRepository<BoardHashtag, String> {

    fun findByNameIn(names: List<String>): List<BoardHashtag>

    fun saveAll(entities: List<BoardHashtag>): List<BoardHashtag>
}