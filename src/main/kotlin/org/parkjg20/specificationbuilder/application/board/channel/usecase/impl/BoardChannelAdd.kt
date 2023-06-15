package org.parkjg20.specificationbuilder.application.board.channel.usecase.impl

import org.hibernate.exception.ConstraintViolationException
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardAddDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.BoardChannelAddUseCase
import org.parkjg20.specificationbuilder.domain.board.entity.BoardChannel
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.common.exception.DuplicatedEntityException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardChannelAdd(
    val boardRepository: BoardChannelEntityRepositoryInterface
) : BoardChannelAddUseCase {

    @Throws(DuplicatedEntityException::class)
    override fun execute(addDto: BoardAddDto): BoardChannelDto {

        val closure =
            @Retryable(maxAttempts = 3)
            @Transactional
            fun (addDto: BoardAddDto): BoardChannelDto {

                val channel = boardRepository.save(BoardChannel.create(addDto))
                return BoardChannelDto.of(channel)
            }

        return try {
            closure(addDto)
        } catch (e: DataIntegrityViolationException) {
            if (ConstraintViolationException::class.java.isAssignableFrom(e.cause!!::class.java)
                && (e.cause as ConstraintViolationException).constraintName.contains("UNIQUE")) {

                throw DuplicatedEntityException()
            }

            throw e
        }
    }

}