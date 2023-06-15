package org.parkjg20.specificationbuilder.sample.domain.board.entity

import org.parkjg20.specificationbuilder.util.StringGenerator
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "board_hashtags")
open class BoardHashtag {
    @Id
    @Column(name = "hash_tag_id", nullable = false, length = 36)
    open var id: String? = null

    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Column(name = "total_post_count", nullable = false)
    open var totalPostCount: Int = 0

    @Column(name = "last_uploaded_at")
    open var lastUploadedAt: Instant? = null

    companion object {
        fun create(name: String): BoardHashtag {
            val entity = BoardHashtag()
            entity.id = StringGenerator.getUuid(false)
            entity.name = name

            return entity
        }
    }

    fun uploadPost() {
        this.totalPostCount.plus(1)
        this.lastUploadedAt = Instant.now()
    }
}