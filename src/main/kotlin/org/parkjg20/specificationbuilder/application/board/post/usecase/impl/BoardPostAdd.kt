package org.parkjg20.specificationbuilder.application.board.post.usecase.impl

import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostAddDto
import org.parkjg20.specificationbuilder.application.board.post.dto.BoardPostDto
import org.parkjg20.specificationbuilder.application.board.post.service.BoardServiceInterface
import org.parkjg20.specificationbuilder.application.board.post.usecase.BoardPostAddUseCase
import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import org.parkjg20.specificationbuilder.domain.board.entity.BoardHashtag
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPost
import org.parkjg20.specificationbuilder.domain.board.entity.BoardPostHashtag
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotAvailableStatusException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException
import org.parkjg20.specificationbuilder.domain.board.repository.BoardChannelEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardHashtagEntityRepositoryInterface
import org.parkjg20.specificationbuilder.domain.board.repository.BoardPostEntityRepositoryInterface
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardPostAdd(
    val boardService: BoardServiceInterface,
    val boardRepository: BoardChannelEntityRepositoryInterface,
    val boardHashtagRepository: BoardHashtagEntityRepositoryInterface,
    val boardPostRepository: BoardPostEntityRepositoryInterface
) : BoardPostAddUseCase {

    @Retryable(maxAttempts = 3)
    @Transactional
    @Throws(BoardChannelNotFoundException::class, BoardChannelNotAvailableStatusException::class)
    override fun execute(addDto: BoardPostAddDto, actor: BoardActor?): BoardPostDto {

        val board = boardRepository.findByIdOrFail(addDto.channelId)
        if (!board.isPostAddable()) {
            throw BoardChannelNotAvailableStatusException()
        }

        val registeredHashtags = boardHashtagRepository.findByNameIn(addDto.hashTags)
        val newHashtags = createNewHashtag(
            addDto.hashTags.filter { tag -> registeredHashtags.none { it.name.equals(tag) }}
        )

        boardHashtagRepository.saveAll(newHashtags)

        val hashtags = registeredHashtags + newHashtags
        val post = boardPostRepository.save(BoardPost.create(addDto, board, actor?.user))

        val boardPostHashtags: List<BoardPostHashtag> =
            hashtags.map {
                BoardPostHashtag.create(post, it)
            }

        post.hashTags = boardPostHashtags.toMutableList()

        boardService.uploadPost(board)
        // 해시태그 수정 후 재저장
        return BoardPostDto.of(boardPostRepository.save(post))
    }

    private fun createNewHashtag(newHashtags: List<String>): List<BoardHashtag> {
        return newHashtags.map {
            BoardHashtag.create(it)
        }
    }
}