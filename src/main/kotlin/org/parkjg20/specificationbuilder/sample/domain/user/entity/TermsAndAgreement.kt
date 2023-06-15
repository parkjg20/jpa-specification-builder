package org.parkjg20.specificationbuilder.sample.domain.user.entity

import org.parkjg20.specificationbuilder.domain.user.enum.TermAndAgreementStatus
import org.parkjg20.specificationbuilder.domain.user.enum.TermAndAgreementType
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "terms_and_agreements")
open class TermsAndAgreement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "term_id", nullable = false)
    open var id: Int? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Instant? = null

    @Column(name = "updated_at")
    open var updatedAt: Instant? = null

    @Column(name = "type", nullable = false)
    open var type: TermAndAgreementType? = null

    @Column(name = "title", nullable = false)
    open var title: String? = null

    @Lob
    @Column(name = "content", nullable = false)
    open var content: String? = null

    @Column(name = "status", nullable = false)
    open var status: TermAndAgreementStatus? = null
}