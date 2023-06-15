package org.parkjg20.specificationbuilder.sample.application.board.channel.usecase

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardStatusUpdateDto
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelStatusNotConvertableException
import org.parkjg20.specificationbuilder.domain.common.exception.StatusAlreadyChangedException
import javax.persistence.EntityNotFoundException

interface BoardChannelStatusUpdateUseCase {

    @Throws(
        EntityNotFoundException::class,
        StatusAlreadyChangedException::class,
        BoardChannelStatusNotConvertableException::class
    )
    fun execute(updateDto: BoardStatusUpdateDto): BoardChannelDto
}