package org.parkjg20.specificationbuilder.sample.domain.auth.repository.jpa

import org.parkjg20.specificationbuilder.domain.auth.entity.Authorization
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorizationRepositoryInterface : JpaRepository<Authorization, String> {
}