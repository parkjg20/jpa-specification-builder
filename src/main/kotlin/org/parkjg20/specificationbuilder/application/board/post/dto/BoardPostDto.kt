package org.parkjg20.specificationbuilder.application.board.post.dto

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardHashtagDto
import org.parkjg20.specificationbuilder.application.board.dto.BoardActorDto
import org.parkjg20.specificationbuilder.application.common.dto.DescribedEnumDto
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.parkjg20.specificationbuilder.domain.board.enum.BoardPostStatus

data class BoardPostDto(
    val postId: String,
    val nestId: Int?,
    val channelId: String,
    val title: String,
    val content: String,
    val status: DescribedEnumDto<BoardPostStatus>,
    val hashTags: List<BoardHashtagDto>,
    val creator: BoardActorDto?,
    val createdAt: String,
    val updatedAt: String?
) {

    companion object {
        fun of(entity: BoardPost): BoardPostDto {
            return BoardPostDto(
                entity.id!!.toString(),
                entity.nestId,
                entity.channel!!.id!!,
                entity.title!!,
                entity.content!!,
                DescribedEnumDto<BoardPostStatus>(
                    entity.status!!,
                    when (entity.status) {
                        BoardPostStatus.CREATED -> "생성됨"
                        BoardPostStatus.BLOCKED -> "차단됨"
                        BoardPostStatus.DELETED -> "삭제됨"
                        else -> "-"
                    }
                ),
                entity.hashTags.map { BoardHashtagDto.of(it.hashTag!!) },
                entity.user?.let { BoardActorDto(it.id!!, it.nickname) },
                entity.createdAt!!.toString(),
                entity.updatedAt?.toString()
            )
        }

    }
}