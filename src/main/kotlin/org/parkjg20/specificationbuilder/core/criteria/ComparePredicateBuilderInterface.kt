package org.parkjg20.specificationbuilder.core.criteria

import org.springframework.data.jpa.domain.Specification

interface ComparePredicateBuilderInterface<T> {
    fun build(value: Comparable<Any>, include: Boolean): Specification<T>

}