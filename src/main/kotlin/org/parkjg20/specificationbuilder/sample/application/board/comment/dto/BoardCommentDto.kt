package org.parkjg20.specificationbuilder.sample.application.board.comment.dto

import org.parkjg20.specificationbuilder.application.board.dto.BoardActorDto
import org.parkjg20.specificationbuilder.application.common.dto.DescribedEnumDto
import org.parkjg20.specificationbuilder.domain.board.entity.BoardComment
import org.parkjg20.specificationbuilder.domain.board.enum.BoardCommentStatus

data class BoardCommentDto(
    val id: String,
    val nestId: Int?,
    val postId: String,
    val content: String,
    val status: DescribedEnumDto<BoardCommentStatus>,
    val creator: BoardActorDto?,
    val createdAt: String,
    val updatedAt: String?
) {
    companion object {
        fun of(entity: BoardComment): BoardCommentDto {
            return BoardCommentDto(
                entity.id!!,
                entity.nestId,
                entity.post!!.id!!,
                entity.content!!,
                DescribedEnumDto(
                    entity.status!!,
                    when (entity.status) {
                        BoardCommentStatus.CREATED -> "생성됨"
                        BoardCommentStatus.BLOCKED -> "차단됨"
                        BoardCommentStatus.DELETED -> "삭제됨"
                        else -> "-"
                    }
                ),
                entity.user?.let { BoardActorDto(it.id!!, it.nickname) },
                entity.createdAt!!.toString(),
                entity.updatedAt?.toString()
            )
        }
    }
}