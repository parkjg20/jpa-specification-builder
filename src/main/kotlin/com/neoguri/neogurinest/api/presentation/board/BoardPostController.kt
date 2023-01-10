package com.neoguri.neogurinest.api.presentation.board

import com.neoguri.neogurinest.api.application.auth.dto.LoginUserDto
import com.neoguri.neogurinest.api.application.board.post.dto.*
import com.neoguri.neogurinest.api.application.board.post.usecase.*
import com.neoguri.neogurinest.api.configuration.security.dto.AccessTokenAuthentication
import com.neoguri.neogurinest.api.domain.board.exception.BoardChannelNotAvailableStatusException
import com.neoguri.neogurinest.api.domain.board.exception.BoardChannelNotFoundException
import com.neoguri.neogurinest.api.domain.board.exception.BoardPostNotFoundException
import com.neoguri.neogurinest.api.domain.common.exception.DuplicatedEntityException
import com.neoguri.neogurinest.api.presentation.BaseController
import com.neoguri.neogurinest.api.presentation.exception.BadRequestException
import com.neoguri.neogurinest.api.presentation.exception.ConflictException
import com.neoguri.neogurinest.api.presentation.exception.ForbiddenException
import com.neoguri.neogurinest.api.presentation.exception.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/board/posts")
class BoardPostController(
    val add: BoardPostAddUseCaseInterface,
    val get: BoardPostGetUseCaseInterface,
    val getMany: BoardPostGetManyUseCaseInterface,
    val update: BoardPostUpdateUseCaseInterface,
    val statusUpdate: BoardPostStatusUpdateUseCaseInterface
) : BaseController() {

    @GetMapping("{postId}")
    fun get(
        @PathVariable("postId") postId: String,
        authentication: AccessTokenAuthentication?
    ): ResponseEntity<BoardPostDto> {

        val actor: BoardPostActorDto? =
            if (authentication == null) {
                null
            } else {
                BoardPostActorDto((authentication.details as LoginUserDto).userId)
            }

        return try {
            val payload = get.execute(postId, actor)
            ResponseEntity
                .ok()
                .body(payload)
        } catch (e: DuplicatedEntityException) {
            throw ConflictException(e.message!!)
        }
    }

    @GetMapping
    fun getMany(
        @RequestBody boardPostFilterDto: BoardPostFilterDto,
        authentication: AccessTokenAuthentication?
    ): ResponseEntity<List<BoardPostDto>> {

        val actor: BoardPostActorDto? =
            if (authentication == null) {
                null
            } else {
                BoardPostActorDto((authentication.details as LoginUserDto).userId)
            }

        return try {
            val payload = getMany.execute(boardPostFilterDto, actor)
            ResponseEntity
                .ok()
                .body(payload)
        } catch (e: DuplicatedEntityException) {
            throw ConflictException(e.message!!)
        }
    }

    @PostMapping
    fun add(
        @RequestBody boardPostAddDto: BoardPostAddDto,
        authentication: AccessTokenAuthentication?
    ): ResponseEntity<BoardPostDto> {
        if (boardPostAddDto.title.trim().isEmpty()) {
            throw BadRequestException("Body/title must be has length over 1")
        }

        if (boardPostAddDto.content.trim().isEmpty()) {
            throw BadRequestException("Body/content must be has length over 1")
        }


        val actor: BoardPostActorDto? =
            if (authentication == null) {
                null
            } else {
                BoardPostActorDto((authentication.details as LoginUserDto).userId)
            }

        return try {
            val boardPost = add.execute(boardPostAddDto, actor)
            ResponseEntity
                .created(URI("/api/board/posts/${boardPost.postId}"))
                .body(boardPost)
        } catch (e: BoardChannelNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw ForbiddenException(e.message!!)
        }
    }

    @PutMapping("/{postId}")
    fun update(@RequestBody boardPostUpdateDto: BoardPostUpdateDto): ResponseEntity<BoardPostDto> {

        return try {
            ResponseEntity.ok(update.execute(boardPostUpdateDto))
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw BadRequestException(e.message!!)
        }
    }

    @PatchMapping("/{postId}/status")
    fun updateStatus(@RequestBody boardPostStatusUpdateDto: BoardPostStatusUpdateDto): ResponseEntity<BoardPostDto> {

        return try {
            ResponseEntity.ok(statusUpdate.execute(boardPostStatusUpdateDto))
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw BadRequestException(e.message!!)
        }
    }
}