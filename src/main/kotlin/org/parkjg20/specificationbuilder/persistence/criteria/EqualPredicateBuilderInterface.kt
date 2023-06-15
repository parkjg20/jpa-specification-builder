package org.parkjg20.specificationbuilder.persistence.criteria

import org.springframework.data.jpa.domain.Specification

interface EqualPredicateBuilderInterface<T> {
    fun <V> build(value: V): Specification<T>


}