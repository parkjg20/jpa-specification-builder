package org.parkjg20.specificationbuilder.core.criteria

import org.springframework.data.jpa.domain.Specification

interface InPredicateBuilderInterface<T> {
    fun <V> build(values: List<V>): Specification<T>
}