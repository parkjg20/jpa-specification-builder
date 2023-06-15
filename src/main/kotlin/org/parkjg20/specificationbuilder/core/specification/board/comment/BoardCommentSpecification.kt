package org.parkjg20.specificationbuilder.core.specification.board.comment

import org.parkjg20.specificationbuilder.application.board.comment.dto.BoardCommentFilterDto
import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.parkjg20.specificationbuilder.persistence.specification.ObjectSpecificationBuilder
import org.springframework.data.jpa.domain.Specification

class BoardCommentSpecification {
    companion object {

        fun of(filterDto: BoardCommentFilterDto): Specification<BoardComment>? {
            return ObjectSpecificationBuilder<BoardCommentFilterDto, BoardComment>(BoardCommentFilterDto::class.java).build(filterDto)
        }
    }
}