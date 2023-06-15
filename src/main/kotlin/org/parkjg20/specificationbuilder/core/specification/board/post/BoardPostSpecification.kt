package org.parkjg20.specificationbuilder.core.specification.board.post

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostFilterDto
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.parkjg20.specificationbuilder.persistence.specification.ObjectSpecificationBuilder
import org.springframework.data.jpa.domain.Specification

class BoardPostSpecification {

    companion object {

        fun of(filterDto: BoardPostFilterDto): Specification<BoardPost>? {
            return ObjectSpecificationBuilder<BoardPostFilterDto, BoardPost>(BoardPostFilterDto::class.java).build(filterDto)
        }
    }

}