package org.parkjg20.specificationbuilder.domain.nest.entity

import org.parkjg20.specificationbuilder.application.nest.dto.request.NestAddDto
import org.parkjg20.specificationbuilder.domain.nest.enum.NestStatus
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "nests")
open class Nest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "nest_id", nullable = false)
    open var id: Int? = null

    @Column(name = "title", nullable = false)
    open var title: String? = null

    @Column(name = "city", nullable = false)
    open var city: String? = null

    @Column(name = "district", nullable = false)
    open var district: String? = null

    @Column(name = "status", nullable = false)
    open var status: NestStatus? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    @Column(name = "last_uploaded_at")
    open var lastUploadedAt: Instant? = null

    companion object {
        fun create(nestAddDto: NestAddDto): Nest {
            val self = Nest()

            self.title = nestAddDto.title
            self.city = nestAddDto.city
            self.district = nestAddDto.district
            self.status = NestStatus.CREATED
            self.createdAt = Instant.now()

            return self
        }
    }

}