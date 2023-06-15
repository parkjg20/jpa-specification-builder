package org.parkjg20.specificationbuilder.sample.domain.nest.repository.jpa

import org.parkjg20.specificationbuilder.domain.nest.entity.Nest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NestRepositoryInterface : JpaRepository<Nest, Int> {

}
