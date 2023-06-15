package org.parkjg20.specificationbuilder.sample.application.board.channel.dto

import org.parkjg20.specificationbuilder.application.common.dto.DescribedEnumDto
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.domain.board.enum.BoardStatus

data class BoardChannelDto(
    val channelId: String,
    val nestId: Int?,
    val title: String,
    val status: DescribedEnumDto<BoardStatus>,
    val createdAt: String,
    val lastUploadedAt: String?
) {
    
    companion object {
        fun of(entity: BoardChannel): BoardChannelDto {

            return BoardChannelDto(
                entity.id!!.toString(),
                entity.nestId,
                entity.title!!,
                DescribedEnumDto(
                    entity.status!!,
                    when(entity.status) {
                        BoardStatus.ACTIVATED -> "활성화됨"
                        BoardStatus.CREATED -> "생성됨"
                        BoardStatus.SUSPENDED -> "사용중지됨"
                        BoardStatus.DELETED -> "삭제됨"
                        else -> "-"
                    }
                ),
                entity.createdAt!!.toString(),
                entity.lastUploadedAt?.toString()
            )
        }
    }
}