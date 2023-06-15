package org.parkjg20.specificationbuilder.domain.user.repository.jpa;

import org.parkjg20.specificationbuilder.domain.user.entity.UserFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFileRepositoryInterface : JpaRepository<UserFile, Int> {

}
