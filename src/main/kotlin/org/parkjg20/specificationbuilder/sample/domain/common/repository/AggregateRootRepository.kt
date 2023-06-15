package org.parkjg20.specificationbuilder.sample.domain.common.repository

interface AggregateRootRepository<EntityType, KeyType> {
    fun save(entity: EntityType): EntityType

    fun findById(id: KeyType): EntityType?
}