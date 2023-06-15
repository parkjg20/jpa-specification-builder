package org.parkjg20.specificationbuilder.sample.domain.board.entity

import org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto
import org.parkjg20.specificationbuilder.domain.board.enum.BoardCommentReportStatus
import org.parkjg20.specificationbuilder.domain.board.enum.BoardCommentReportType
import org.parkjg20.specificationbuilder.domain.user.entity.User
import org.parkjg20.specificationbuilder.util.StringGenerator
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "board_comment_reports")
open class BoardCommentReport {
    @Id
    @Column(name = "report_id", nullable = false, length = 36)
    open var id: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    open var comment: BoardComment? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @Lob
    @Column(name = "content", nullable = false)
    open var content: String? = null

    @Lob
    @Column(name = "reported_content", nullable = false)
    open var reportedContent: String? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    @Column(name = "updated_at")
    open var updatedAt: Instant? = null

    @Column(name = "type", nullable = false)
    open var type: BoardCommentReportType? = null

    @Column(name = "status", nullable = false)
    open var status: BoardCommentReportStatus? = null

    companion object {
        fun create(addDto: org.parkjg20.specificationbuilder.sample.application.board.action.comment.dto.BoardCommentReportAddDto, comment: BoardComment, actor: User): BoardCommentReport {
            val entity = BoardCommentReport()
            entity.id = StringGenerator.getUuid(false)
            entity.type = addDto.type
            entity.comment = comment
            entity.user = actor
            entity.content = addDto.content
            entity.reportedContent = comment.content
            entity.createdAt = Instant.now()
            entity.status = BoardCommentReportStatus.CREATED

            return entity
        }
    }

}