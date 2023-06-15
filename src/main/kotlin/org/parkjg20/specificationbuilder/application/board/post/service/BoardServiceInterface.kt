package org.parkjg20.specificationbuilder.application.board.post.service

import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel

interface BoardServiceInterface {
    fun uploadPost(board: BoardChannel): BoardChannel

}