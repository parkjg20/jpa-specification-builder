package com.neoguri.neogurinest.api.application.board.post.usecase.impl

import com.neoguri.neogurinest.api.application.board.post.dto.BoardPostActorDto
import com.neoguri.neogurinest.api.application.board.post.dto.BoardPostAddDto
import com.neoguri.neogurinest.api.application.board.post.dto.BoardPostDto
import com.neoguri.neogurinest.api.application.board.post.service.BoardServiceInterface
import com.neoguri.neogurinest.api.application.board.post.usecase.BoardPostAddUseCaseInterface
import com.neoguri.neogurinest.api.domain.board.entity.BoardHashtag
import com.neoguri.neogurinest.api.domain.board.entity.BoardPost
import com.neoguri.neogurinest.api.domain.board.entity.BoardPostHashtag
import com.neoguri.neogurinest.api.domain.board.exception.BoardNotAvailableStatusException
import com.neoguri.neogurinest.api.domain.board.repository.BoardChannelEntityRepositoryInterface
import com.neoguri.neogurinest.api.domain.board.repository.BoardHashtagEntityRepositoryInterface
import com.neoguri.neogurinest.api.domain.board.repository.BoardPostEntityRepositoryInterface
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class BoardPostAddUseCase(
    val boardService: BoardServiceInterface,
    val boardRepository: BoardChannelEntityRepositoryInterface,
    val boardHashtagRepository: BoardHashtagEntityRepositoryInterface,
    val boardPostRepository: BoardPostEntityRepositoryInterface
) : BoardPostAddUseCaseInterface {

    @Retryable(maxAttempts = 3)
    @Transactional
    @Throws(EntityNotFoundException::class, BoardNotAvailableStatusException::class)
    override fun execute(addDto: BoardPostAddDto, actor: BoardPostActorDto?): BoardPostDto {

        val board = boardRepository.findByIdOrFail(addDto.boardId)
        if (!board.isPostAddable()) {
            throw BoardNotAvailableStatusException()
        }

        val registeredHashtags = boardHashtagRepository.findByNameIn(addDto.hashTags)
        val newHashtags = createNewHashtag(
            addDto.hashTags.filter { tag -> registeredHashtags.none { it.name.equals(tag) }}
        )

        boardHashtagRepository.saveAll(newHashtags)

        val hashtags = registeredHashtags + newHashtags
        val post = boardPostRepository.save(BoardPost.create(addDto, board, actor))

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