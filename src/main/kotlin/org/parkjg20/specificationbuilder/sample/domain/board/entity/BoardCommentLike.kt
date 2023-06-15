package org.parkjg20.specificationbuilder.sample.domain.board.entity

import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "board_comment_likes")
open class BoardCommentLike {
    @EmbeddedId
    open var id: BoardCommentLikeId? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    companion object {

        fun create(post: BoardComment, actor: BoardActor): BoardCommentLike {
            val entity = BoardCommentLike()
            entity.id = BoardCommentLikeId(post.id, actor.user.id)
            entity.createdAt = Instant.now()

            return entity
        }

    }

}