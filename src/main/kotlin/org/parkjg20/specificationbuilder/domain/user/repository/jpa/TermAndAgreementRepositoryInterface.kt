package org.parkjg20.specificationbuilder.domain.user.repository.jpa;

import org.parkjg20.specificationbuilder.domain.user.entity.TermsAndAgreement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TermAndAgreementRepositoryInterface : JpaRepository<TermsAndAgreement, Int> {

}
