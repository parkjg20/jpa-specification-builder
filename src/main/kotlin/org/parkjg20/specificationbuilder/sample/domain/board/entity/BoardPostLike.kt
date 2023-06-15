package org.parkjg20.specificationbuilder.sample.domain.board.entity

import org.parkjg20.specificationbuilder.domain.board.bean.BoardActor
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "board_post_likes")
open class BoardPostLike {
    @EmbeddedId
    open var id: BoardPostLikeId? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    companion object {

        fun create(post: BoardPost, actor: BoardActor): BoardPostLike {
            val entity = BoardPostLike()
            entity.id = BoardPostLikeId(post.id, actor.user.id)
            entity.createdAt = Instant.now()

            return entity
        }

    }

}