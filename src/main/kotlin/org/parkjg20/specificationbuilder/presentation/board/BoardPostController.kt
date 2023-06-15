package org.parkjg20.specificationbuilder.presentation.board

import org.parkjg20.specificationbuilder.application.aspect.BoardContext
import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostBookmarkAddUseCase
import org.parkjg20.specificationbuilder.application.board.action.post.usecase.BoardPostLikeAddUseCase
import org.parkjg20.specificationbuilder.application.board.post.dto.*
import org.parkjg20.specificationbuilder.application.board.post.usecase.*
import org.parkjg20.specificationbuilder.application.common.dto.*
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotAvailableStatusException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.BoardPostNotFoundException
import org.parkjg20.specificationbuilder.domain.board.exception.ModifyingOtherUsersPostException
import org.parkjg20.specificationbuilder.domain.common.CursorStringParser
import org.parkjg20.specificationbuilder.presentation.BaseController
import org.parkjg20.specificationbuilder.presentation.exception.BadRequestException
import org.parkjg20.specificationbuilder.presentation.exception.ForbiddenException
import org.parkjg20.specificationbuilder.presentation.exception.NotFoundException
import org.parkjg20.specificationbuilder.presentation.exception.UnauthorizedException
import org.parkjg20.specificationbuilder.presentation.param.OrderDtoBuilder
import org.parkjg20.specificationbuilder.util.Decoder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/board/posts")
class BoardPostController(
    val add: BoardPostAddUseCase,
    val get: BoardPostGetUseCase,
    val getManyUsingPagination: BoardPostGetManyUsingPaginationUseCase,
    val getManyUsingCursor: BoardPostGetManyUsingCursorUseCase,
    val update: BoardPostUpdateUseCase,
    val statusUpdate: BoardPostStatusUpdateUseCase,
    val likeAdd: BoardPostLikeAddUseCase,
    val bookmarkAdd: BoardPostBookmarkAddUseCase,
    val cursorStringParser: CursorStringParser
) : BaseController() {

    @GetMapping("{postId}")
    fun get(
        @PathVariable("postId") postId: String,
    ): ResponseEntity<BoardPostDto> {
        val boardContext = BoardContext.getInstance()
        return try {
            val payload = get.execute(postId, boardContext.actor)
            ResponseEntity
                .ok()
                .body(payload)
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(postId)
        }
    }


    @GetMapping("/paginated")
    fun getManyUsingPagination(
        @ModelAttribute filter: BoardPostFilterDto,
        @ModelAttribute pagination: PaginationRequestDto
    ): ResponseEntity<PaginatedResultDto<BoardPostDto>> {
        val boardContext = BoardContext.getInstance()

        val payload = getManyUsingPagination.execute(
            filter,
            PaginationDto.of(pagination),
            boardContext.actor
        )
        return ResponseEntity.ok().body(payload)
    }


    @GetMapping("/cursor-paginated")
    fun getManyUsingCursorPagination(
        @ModelAttribute filter: BoardPostFilterDto,
        @ModelAttribute paginationRequest: CursorPaginationRequestDto,
    ): ResponseEntity<CursorPaginatedResultDto<BoardPostDto>> {
        val boardContext = BoardContext.getInstance()
        val pagination = CursorPaginationDto(
            cursorStringParser.parse(paginationRequest.cursor?.let { Decoder decodes it }),
            paginationRequest.size
        )
        val payload = getManyUsingCursor.execute(
            filter,
            pagination,
            OrderDtoBuilder(paginationRequest.order).build(),
            boardContext.actor
        )
        return ResponseEntity
            .ok()
            .body(payload)
    }

    @PostMapping
    fun add(
        @RequestBody boardPostAddDto: BoardPostAddDto,
    ): ResponseEntity<BoardPostDto> {
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        if (boardPostAddDto.title.trim().isEmpty()) {
            throw BadRequestException("Body/title must be has length over 1")
        }

        if (boardPostAddDto.content.trim().isEmpty()) {
            throw BadRequestException("Body/content must be has length over 1")
        }


        return try {
            val boardPost = add.execute(boardPostAddDto, boardContext.actor)
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
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        return try {
            ResponseEntity.ok(update.execute(boardPostUpdateDto, boardContext.actor!!))
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw BadRequestException(e.message!!)
        } catch (e: ModifyingOtherUsersPostException) {
            throw ForbiddenException(e.message!!)
        }
    }

    @PatchMapping("/{postId}/status")
    fun updateStatus(
        @PathVariable("postId") postId: String,
        @RequestBody boardPostStatusUpdateDto: BoardPostStatusUpdateDto
    ): ResponseEntity<BoardPostDto> {
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        return try {
            ResponseEntity.ok(statusUpdate.execute(postId, boardPostStatusUpdateDto, boardContext.actor!!))
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw BadRequestException(e.message!!)
        } catch (e: ModifyingOtherUsersPostException) {
            throw ForbiddenException(e.message!!)
        }
    }

    @PostMapping("/{postId}/likes")
    fun like(
        @PathVariable("postId") postId: String
    ): ResponseEntity<Boolean> {
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        return try {
            ResponseEntity.ok(likeAdd.execute(postId, boardContext.actor!!))
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw BadRequestException(e.message!!)
        } catch (e: ModifyingOtherUsersPostException) {
            throw ForbiddenException(e.message!!)
        }
    }

    @PostMapping("/{postId}/bookmarks")
    fun bookmark(
        @PathVariable("postId") postId: String
    ): ResponseEntity<Boolean> {
        val boardContext = BoardContext.getInstance()
        if (boardContext.isAnonymous()) {
            throw UnauthorizedException("You must be authorized for this api")
        }

        return try {
            ResponseEntity.ok(bookmarkAdd.execute(postId, boardContext.actor!!))
        } catch (e: BoardPostNotFoundException) {
            throw NotFoundException(e.message!!)
        } catch (e: BoardChannelNotAvailableStatusException) {
            throw BadRequestException(e.message!!)
        } catch (e: ModifyingOtherUsersPostException) {
            throw ForbiddenException(e.message!!)
        }
    }
}