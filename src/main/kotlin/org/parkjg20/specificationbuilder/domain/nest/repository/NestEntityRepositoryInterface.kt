package org.parkjg20.specificationbuilder.domain.nest.repository

import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import org.parkjg20.specificationbuilder.domain.nest.entity.Nest

interface NestEntityRepositoryInterface : AggregateRootRepository<Nest, Int> {

    fun findByIdOrFail(id: Int): Nest

}