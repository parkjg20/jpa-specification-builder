package org.parkjg20.specificationbuilder.persistence.specification

import org.springframework.data.jpa.domain.Specification

interface SpecificationBuilderInterface<F, E> {

    fun build(from: F): Specification<E>?
}