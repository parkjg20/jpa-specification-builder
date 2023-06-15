package org.parkjg20.specificationbuilder.core.criteria

import org.parkjg20.specificationbuilder.persistence.specification.RangeInterface
import org.springframework.data.jpa.domain.Specification

interface BetweenPredicateBuilderInterface<T> {
    fun build(value: RangeInterface<Comparable<Any>>): Specification<T>

}