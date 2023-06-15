package org.parkjg20.specificationbuilder.sample.application.board.channel.dto

import org.parkjg20.specificationbuilder.domain.board.entity.BoardHashtag

data class BoardHashtagDto(
    val hashtagId: String,
    val name: String
) {

    companion object {
        fun of(entity: BoardHashtag): BoardHashtagDto {

            return BoardHashtagDto(
                entity.id!!.toString(),
                entity.name!!
            )
        }
    }
}