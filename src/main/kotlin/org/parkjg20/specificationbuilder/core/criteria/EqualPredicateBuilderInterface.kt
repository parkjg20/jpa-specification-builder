package org.parkjg20.specificationbuilder.core.criteria

import org.springframework.data.jpa.domain.Specification

interface EqualPredicateBuilderInterface<T> {
    fun <V> build(value: V): Specification<T>


}