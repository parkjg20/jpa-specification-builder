package org.parkjg20.specificationbuilder.core.specification.board.post

import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.parkjg20.specificationbuilder.domain.common.Cursor
import org.parkjg20.specificationbuilder.persistence.specification.CursorSpecificationBuilder
import org.springframework.data.jpa.domain.Specification


class BoardPostCursorSpecification {

    companion object {
        fun of(list: List<Cursor>): Specification<BoardPost>? {

            return CursorSpecificationBuilder<BoardPost>().build(list)
        }
    }

}