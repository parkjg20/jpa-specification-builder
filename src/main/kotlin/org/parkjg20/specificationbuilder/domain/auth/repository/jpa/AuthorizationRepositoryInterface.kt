package org.parkjg20.specificationbuilder.domain.auth.repository.jpa

import com.neoguri.neogurinest.api.domain.auth.entity.Authorization
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorizationRepositoryInterface : JpaRepository<Authorization, String> {
}