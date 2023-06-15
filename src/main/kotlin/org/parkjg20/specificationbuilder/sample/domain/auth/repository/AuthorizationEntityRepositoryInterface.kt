package org.parkjg20.specificationbuilder.sample.domain.auth.repository

import org.parkjg20.specificationbuilder.domain.auth.entity.Authorization
import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import javax.persistence.EntityNotFoundException

interface AuthorizationEntityRepositoryInterface : AggregateRootRepository<Authorization, String> {

    @Throws(EntityNotFoundException::class)
    fun findByAccessTokenOrFail(accessToken: String): Authorization

    @Throws(EntityNotFoundException::class)
    fun findByRefreshTokenOrFail(refreshToken: String): Authorization

}