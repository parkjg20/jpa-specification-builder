package org.parkjg20.specificationbuilder.sample.presentation.board

import org.parkjg20.specificationbuilder.application.aspect.BoardContext
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardAddDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardChannelFilterDto
import org.parkjg20.specificationbuilder.application.board.channel.dto.BoardStatusUpdateDto
import org.parkjg20.specificationbuilder.application.board.channel.usecase.*
import org.parkjg20.specificationbuilder.application.common.dto.*
import org.parkjg20.specificationbuilder.domain.board.exception.BoardChannelStatusNotConvertableException
import org.parkjg20.specificationbuilder.domain.common.CursorStringParser
import org.parkjg20.specificationbuilder.domain.common.exception.DuplicatedEntityException
import org.parkjg20.specificationbuilder.domain.common.exception.StatusAlreadyChangedException
import org.parkjg20.specificationbuilder.presentation.BaseController
import org.parkjg20.specificationbuilder.presentation.exception.BadRequestException
import org.parkjg20.specificationbuilder.presentation.exception.ConflictException
import org.parkjg20.specificationbuilder.presentation.param.OrderDtoBuilder
import org.parkjg20.specificationbuilder.util.Decoder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/board/channels")
class BoardChannelController(
    val get: BoardChannelGetUseCase,
    val getMany: BoardChannelGetManyUseCase,
    val getManyUsingPagination: BoardChannelGetManyUsingPaginationUseCase,
    val getManyUsingCursor: BoardChannelGetManyUsingCursorUseCase,
    val add: BoardChannelAddUseCase,
    val updateStatus: BoardChannelStatusUpdateUseCase,
    val cursorStringParser: CursorStringParser
) : BaseController() {

    @GetMapping("/{channelId}")
    fun get(@PathVariable("channelId") channelId: String): ResponseEntity<BoardChannelDto> {

        return ResponseEntity.ok().body(get.execute(channelId))
    }

    @GetMapping("")
    fun getMany(filterDto: BoardChannelFilterDto): ResponseEntity<List<BoardChannelDto>> {

        return ResponseEntity.ok().body(getMany.execute(filterDto))
    }

    @GetMapping("/paginated")
    fun getManyUsingPagination(
        @ModelAttribute filter: BoardChannelFilterDto,
        @ModelAttribute pagination: PaginationRequestDto
    ): ResponseEntity<PaginatedResultDto<BoardChannelDto>> {
        val boardContext = BoardContext.getInstance()

        return ResponseEntity.ok().body(
            getManyUsingPagination.execute(
                filter,
                PaginationDto.of(pagination),
                boardContext.actor
            )
        )
    }

    @GetMapping("/cursor-paginated")
    fun getManyUsingCursor(
        @ModelAttribute filter: BoardChannelFilterDto,
        @ModelAttribute paginationRequest: CursorPaginationRequestDto
    ): ResponseEntity<CursorPaginatedResultDto<BoardChannelDto>> {
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

        return ResponseEntity.ok().body(payload)
    }

    @PostMapping("")
    fun add(@RequestBody boardAddDto: BoardAddDto): ResponseEntity<BoardChannelDto> {
        if (boardAddDto.title.trim().isEmpty()) {
            throw BadRequestException("Body/title must be has length over 1")
        }

        return try {
            val board = add.execute(boardAddDto)
            ResponseEntity
                .created(URI("/api/board/channels/${board.channelId}"))
                .body(board)
        } catch (e: DuplicatedEntityException) {
            throw ConflictException(e.message!!)
        }
    }

    @PutMapping("/{boardId}/status")
    fun updateStatus(@RequestBody boardStatusUpdateDto: BoardStatusUpdateDto): ResponseEntity<BoardChannelDto> {

        return try {
            ResponseEntity.ok(updateStatus.execute(boardStatusUpdateDto))
        } catch (e: BoardChannelStatusNotConvertableException) {
            throw BadRequestException(e.message!!)
        } catch (e: StatusAlreadyChangedException) {
            throw ConflictException(e.message!!)
        }
    }
}