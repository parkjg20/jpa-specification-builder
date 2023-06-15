package org.parkjg20.specificationbuilder.application.board.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostUpdateDto
import org.parkjg20.specificationbuilder.application.board.post.usecase.BoardPostUpdateUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardHashtag
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostHashtag
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotAvailableStatusException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.ModifyingOtherUsersPostException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardHashtagEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardPostUpdate(
    val boardRepository: BoardChannelEntityRepositoryInterface,
    val boardHashtagRepository: BoardHashtagEntityRepositoryInterface,
    val boardPostRepository: BoardPostEntityRepositoryInterface
) : BoardPostUpdateUseCase {

    @Throws(
        BoardChannelNotFoundException::class,
        BoardPostNotFoundException::class,
        BoardChannelNotAvailableStatusException::class,
        ModifyingOtherUsersPostException::class
    )
    override fun execute(updateDto: BoardPostUpdateDto, actor: BoardActor): BoardPostDto {
        return executeImpl(updateDto, actor)
    }


    @Retryable(maxAttempts = 3)
    @Transactional
    fun executeImpl(updateDto: BoardPostUpdateDto, actor: BoardActor): BoardPostDto {
        val post = boardPostRepository.findByIdOrFail(updateDto.postId)
        if (post.userId != actor.user.id) {
            throw ModifyingOtherUsersPostException()
        }

        val targetBoardId = updateDto.boardId ?: post.channel!!.id
        val board = boardRepository.findByIdOrFail(targetBoardId!!)
        if (!board.isPostAddable()) {
            throw BoardChannelNotAvailableStatusException()
        }

        val registeredHashtags = boardHashtagRepository.findByNameIn(updateDto.hashTags)
        val newHashtags = createNewHashtag(
            updateDto.hashTags.filter { tag -> registeredHashtags.none { it.name.equals(tag) } }
        )
        boardHashtagRepository.saveAll(newHashtags)

        val hashtags = registeredHashtags + newHashtags
        val boardPostHashtags =
            hashtags.map {
                BoardPostHashtag.create(post, it)
            }

        post.update(updateDto, board, boardPostHashtags)
        return BoardPostDto.of(boardPostRepository.save(post))
    }

    private fun createNewHashtag(newHashtags: List<String>): List<BoardHashtag> {
        return newHashtags.map {
            BoardHashtag.create(it)
        }
    }
}