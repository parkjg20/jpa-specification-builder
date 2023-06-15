package org.parkjg20.specificationbuilder.application.board.channel.dto

import com.neoguri.neogurinest.api.domain.board.entity.BoardHashtag

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