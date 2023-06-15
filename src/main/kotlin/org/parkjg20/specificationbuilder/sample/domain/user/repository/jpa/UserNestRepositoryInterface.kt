package org.parkjg20.specificationbuilder.sample.domain.user.repository.jpa;

import org.parkjg20.specificationbuilder.domain.user.entity.UserNest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserNestRepositoryInterface : JpaRepository<UserNest, Int> {

}
