package org.parkjg20.specificationbuilder.application.board.channel.usecase.impl

import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardStatusUpdateDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.BoardChannelStatusUpdateUseCase
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelStatusNotConvertableException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.exception.StatusAlreadyChangedException
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class BoardChannelStatusUpdate(
    val boardRepository: BoardChannelEntityRepositoryInterface
) : BoardChannelStatusUpdateUseCase {

    @Throws(
        EntityNotFoundException::class,
        StatusAlreadyChangedException::class,
        BoardChannelStatusNotConvertableException::class
    )
    override fun execute(updateDto: BoardStatusUpdateDto): BoardChannelDto {

        val closure =
            @Retryable(maxAttempts = 3)
            @Transactional
            fun(updateDto: BoardStatusUpdateDto): BoardChannelDto {
                val board = boardRepository.findByIdOrFail(updateDto.boardId)

                if (updateDto.status === board.status) {
                    throw StatusAlreadyChangedException()
                }

                if (!board.isStatusConvertable(updateDto.status)) {
                    throw BoardChannelStatusNotConvertableException()
                }

                board.status = updateDto.status
                return BoardChannelDto.of(boardRepository.save(board))
            }

        return closure(updateDto)
    }

}