package org.parkjg20.specificationbuilder.core.criteria

import org.parkjg20.specificationbuilder.persistence.specification.LikeInterface
import org.springframework.data.jpa.domain.Specification

interface LikePredicateBuilderInterface<T> {
    fun build(value: LikeInterface<out CharSequence>): Specification<T>

}